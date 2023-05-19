package com.jewelbank.api.service.exceptions

import java.lang.RuntimeException

class EntityNotFoundException(cause: String): RuntimeException(cause)