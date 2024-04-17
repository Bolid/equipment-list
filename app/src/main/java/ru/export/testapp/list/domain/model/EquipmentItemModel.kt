package ru.export.testapp.list.domain.model

class EquipmentItemModel(
    val id: String,
    val code: String,
    val name: String,
    val criticality: String,
    val status: String,
    val statusCode: StatusCode,
    val criticalityCode: CriticalityCode
)

enum class StatusCode {
    INSTALLED,
    WITHDRAWN,
    STATUS_UNKNOWN
    ;

    companion object {
        fun getStatusCode(code: String): StatusCode = when (code) {
            STATUS_CODE_INSTALLED -> INSTALLED
            STATUS_CODE_WITHDRAWN -> WITHDRAWN
            else -> STATUS_UNKNOWN
        }

        private const val STATUS_CODE_INSTALLED = "installed"
        private const val STATUS_CODE_WITHDRAWN = "withdrawn"
    }
}

enum class CriticalityCode {
    CRITICALITY_1,
    CRITICALITY_3,
    CRITICALITY_4,
    CRITICALITY_5,
    CRITICALITY_UNKNOWN
    ;

    companion object {
        fun getCriticalityCode(code: String): CriticalityCode = when (code) {
            CRITICALITY_CODE_1, CRITICALITY_CODE_2 -> CRITICALITY_1
            CRITICALITY_CODE_3 -> CRITICALITY_3
            CRITICALITY_CODE_4 -> CRITICALITY_4
            CRITICALITY_CODE_5 -> CRITICALITY_5
            else -> CRITICALITY_UNKNOWN
        }

        private const val CRITICALITY_CODE_1 = "1"
        private const val CRITICALITY_CODE_2 = "2"
        private const val CRITICALITY_CODE_3 = "3"
        private const val CRITICALITY_CODE_4 = "4"
        private const val CRITICALITY_CODE_5 = "5"
    }
}