import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Converter(autoApply = true)
class ZonedDateTimeAttributeConverter : AttributeConverter<ZonedDateTime, LocalDateTime> {

    override fun convertToDatabaseColumn(attribute: ZonedDateTime?): LocalDateTime? {
        return attribute?.withZoneSameInstant(ZoneId.of("UTC"))?.toLocalDateTime()
    }

    override fun convertToEntityAttribute(dbData: LocalDateTime?): ZonedDateTime? {
        val systemZone = ZoneId.systemDefault()
        return dbData?.atZone(ZoneId.of("UTC"))?.withZoneSameInstant(systemZone)
    }
}
