#version 140

in vec2 position;
out vec2 textureCoords;

uniform vec4 matColor;
uniform mat4 projection;
out vec4 color;

void main(void) {
    vec2 worldPosition = (position * 1000) ;

    gl_Position = projection * vec4(worldPosition, 0.0, 1.0);
    color = matColor;
}