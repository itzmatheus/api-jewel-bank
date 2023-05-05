package com.jewelbank.api.service.exceptions

import java.lang.RuntimeException

class BankUserNotFoundException(cause: String): RuntimeException(cause)