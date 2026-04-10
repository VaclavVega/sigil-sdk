package com.grafana.sigil.sdk;

/**
 * Controls what content is included in exported generation payloads and OTel
 * span attributes.
 */
public enum ContentCaptureMode {
    /**
     * Uses the parent or client-level default. On config this resolves to
     * {@link #NO_TOOL_CONTENT} for backward compatibility. On GenerationStart
     * this inherits from config. On ToolExecutionStart this inherits from the
     * parent generation context, falling back to config.
     */
    DEFAULT,
    /** Exports all content. */
    FULL,
    /**
     * Exports full generation content but excludes tool execution content
     * (arguments and results) from span attributes unless explicitly opted in
     * via {@code includeContent} or a per-tool ContentCapture override. This
     * matches the pre-ContentCaptureMode SDK default behavior.
     */
    NO_TOOL_CONTENT,
    /**
     * Preserves message structure, tool names, usage, and timing but strips
     * text, tool arguments, tool results, thinking, system prompts, and raw
     * artifacts.
     *
     * <p>Note: user-provided Metadata and Tags are NOT stripped — callers are
     * responsible for ensuring these maps do not contain sensitive content when
     * using MetadataOnly mode.</p>
     */
    METADATA_ONLY;

    /** Returns the string used in generation metadata markers. */
    public String toMetadataValue() {
        return switch (this) {
            case FULL -> "full";
            case NO_TOOL_CONTENT -> "no_tool_content";
            case METADATA_ONLY -> "metadata_only";
            case DEFAULT -> "";
        };
    }
}
