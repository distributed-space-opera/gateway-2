// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: gateway-comm.proto

package org.gateway.protos;

public interface ReplyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:stream.Reply)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string masterip = 1;</code>
   * @return The masterip.
   */
  java.lang.String getMasterip();
  /**
   * <code>string masterip = 1;</code>
   * @return The bytes for masterip.
   */
  com.google.protobuf.ByteString
      getMasteripBytes();

  /**
   * <pre>
   * Will return ERROR or SUCCESS
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The message.
   */
  java.lang.String getMessage();
  /**
   * <pre>
   * Will return ERROR or SUCCESS
   * </pre>
   *
   * <code>string message = 2;</code>
   * @return The bytes for message.
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <pre>
   * Valid for 1 hour
   * </pre>
   *
   * <code>string token = 3;</code>
   * @return The token.
   */
  java.lang.String getToken();
  /**
   * <pre>
   * Valid for 1 hour
   * </pre>
   *
   * <code>string token = 3;</code>
   * @return The bytes for token.
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
