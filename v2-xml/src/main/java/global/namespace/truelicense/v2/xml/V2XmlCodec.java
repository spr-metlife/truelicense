/*
 * Copyright (C) 2005 - 2019 Schlichtherle IT Services.
 * All rights reserved. Use is subject to license terms.
 */
package global.namespace.truelicense.v2.xml;

import global.namespace.fun.io.api.Decoder;
import global.namespace.fun.io.api.Encoder;
import global.namespace.fun.io.api.Socket;
import global.namespace.truelicense.api.codec.Codec;
import global.namespace.truelicense.obfuscate.Obfuscate;

import java.io.InputStream;
import java.io.OutputStream;

import static global.namespace.fun.io.jaxb.JAXB.xml;

/**
 * A codec for use with V2/XML format license keys.
 */
final class V2XmlCodec implements Codec {

    @Obfuscate
    private static final String APPLICATION_XML_WITH_UTF_8 = "application/xml; charset=utf-8";

    @Obfuscate
    private static final String EIGHT_BIT = "8bit";

    private final global.namespace.fun.io.api.Codec codec;

    V2XmlCodec(V2XmlCodecFactory factory) {
        this.codec = xml(factory.jaxbContext(), factory::configure, factory::configure);
    }

    /**
     * {@inheritDoc}
     * <p>
     * The implementation in the class {@link V2XmlCodec} returns {@code "application/xml; charset=utf-8"}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc3023">RFC 3023</a>
     */
    @Override
    public String contentType() {
        return APPLICATION_XML_WITH_UTF_8;
    }

    /**
     * {@inheritDoc}
     * <p>
     * The implementation in the class {@link V2XmlCodec} returns {@code "8bit"}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc3023">RFC 3023</a>
     */
    @Override
    public String contentTransferEncoding() {
        return EIGHT_BIT;
    }

    @Override
    public Encoder encoder(Socket<OutputStream> output) {
        return codec.encoder(output);
    }

    @Override
    public Decoder decoder(Socket<InputStream> input) {
        return codec.decoder(input);
    }
}
