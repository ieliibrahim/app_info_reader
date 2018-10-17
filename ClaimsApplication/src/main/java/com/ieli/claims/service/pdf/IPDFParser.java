package com.ieli.claims.service.pdf;

import java.io.IOException;
import java.io.InputStream;

import com.ieli.claims.model.app.ApplicationInfo;

public interface IPDFParser {

	ApplicationInfo getPDFConent(InputStream inputStream) throws IOException;
}
