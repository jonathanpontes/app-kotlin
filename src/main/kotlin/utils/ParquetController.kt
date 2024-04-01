package br.com.alura.utils

import org.apache.hadoop.fs.Path
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parquet")
class ParquetController(private val parquetFileReaderService: ParquetFileReaderService) {

    @GetMapping
    fun readParquetFile(): List<Map<String, Any>> {
        val resource: Resource = ClassPathResource("Iris.parquet")
        val hadoopPath = Path(resource.uri)
        return parquetFileReaderService.readParquetFile(hadoopPath)
    }
}