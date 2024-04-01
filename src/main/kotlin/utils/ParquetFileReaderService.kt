package br.com.alura.utils

import org.apache.hadoop.fs.Path
import org.apache.parquet.hadoop.ParquetReader
import org.springframework.stereotype.Service

@Service
class ParquetFileReaderService {

    fun readParquetFile(filePath: Path): List<Map<String, Any>> {
        val reader: ParquetReader<Map<String, Any>> = ParquetReader.builder(MapReadSupport(), filePath).build()

        val result = mutableListOf<Map<String, Any>>()
        var record: Map<String, Any>?
        try {
            while (true) {
                record = reader.read()
                if (record == null) break
                result.add(record)
            }
        } finally {
            reader.close()
        }

        return result
    }

}