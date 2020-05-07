package com.example.prepotency.app.http.helper

import android.util.JsonToken
import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.lang.reflect.Type
import java.nio.charset.Charset

class GameGsonConverterFactory private constructor(private val gson: Gson) : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GameGsonResponseBodyConverter(
            gson,
            adapter
        )
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GameGsonRequestBodyConverter(
            gson,
            adapter
        )
    }

    companion object {
        @JvmStatic
        fun create(): GameGsonConverterFactory {
            return create(
                Gson()
            )
        }

        @JvmStatic
        fun create(gson: Gson?): GameGsonConverterFactory {
            if (gson == null) throw NullPointerException("gson == null")
            return GameGsonConverterFactory(
                gson
            )
        }
    }
}

class GameGsonRequestBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) :
    Converter<T, RequestBody> {

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer = OutputStreamWriter(buffer.outputStream(),
            UTF_8
        )
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return buffer.readByteString().toRequestBody(MEDIA_TYPE)
    }

    companion object {
        private val MEDIA_TYPE = "application/json; charset=UTF-8".toMediaType()
        private val UTF_8 = Charset.forName("UTF-8")
    }
}

@Suppress("INCOMPATIBLE_ENUM_COMPARISON")
class GameGsonResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {

        // 在这里通过 value 拿到 json 字符串进行解析
        // 判断状态码是失败的情况，就抛出异常

        val jsonReader = gson.newJsonReader(value.charStream())
        value.use {
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            return result
        }
    }
}