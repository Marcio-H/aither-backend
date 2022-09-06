@AnyMetaDef(name = "RecursoMetaDef", metaType = "string", idType = "int",
		metaValues = {
				@MetaValue(value = "F", targetEntity = RecursoFrontend.class),
				@MetaValue(value = "B", targetEntity = RecursoBackend.class)
		}
)

package br.com.ifsc.aither.backend.domain;

import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.MetaValue;