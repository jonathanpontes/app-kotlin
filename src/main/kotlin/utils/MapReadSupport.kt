package br.com.alura.utils

import org.apache.hadoop.conf.Configuration
import org.apache.parquet.hadoop.api.ReadSupport
import org.apache.parquet.io.api.*
import org.apache.parquet.schema.MessageType

class MapReadSupport : ReadSupport<Map<String, Any>>() {

    override fun init(
        configuration: Configuration,
        keyValueMetaData: MutableMap<String, String>,
        fileSchema: MessageType
    ): ReadContext {
        return ReadContext(fileSchema)
    }

    override fun prepareForRead(
        configuration: Configuration,
        keyValueMetaData: MutableMap<String, String>,
        fileSchema: MessageType,
        readContext: ReadContext
    ): RecordMaterializer<Map<String, Any>> {
        return object : RecordMaterializer<Map<String, Any>>() {
            private val currentRecord = mutableMapOf<String, Any>()

            override fun getCurrentRecord(): Map<String, Any> {
                return currentRecord
            }

            override fun getRootConverter(): GroupConverter {
                return object : GroupConverter() {
                    override fun getConverter(fieldIndex: Int): Converter {
                        return object : Converter() {
                            override fun isPrimitive(): Boolean {
                                return true
                            }

                            override fun asPrimitiveConverter(): PrimitiveConverter {
                                return object : PrimitiveConverter() {
                                    override fun addBinary(value: Binary) {
                                        currentRecord[fileSchema.getFieldName(fieldIndex)] = value.toStringUsingUTF8()
                                    }

                                    override fun addDouble(value: Double) {
                                        currentRecord[fileSchema.getFieldName(fieldIndex)] = value
                                    }
                                }
                            }
                        }
                    }

                    override fun start() {
                        currentRecord.clear()
                    }

                    override fun end() {
                        // Do nothing
                    }
                }
            }
        }
    }
}