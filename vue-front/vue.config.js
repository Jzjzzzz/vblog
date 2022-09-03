const port = process.env.port || process.env.npm_config_port || 8888
module.exports = {
    publicPath: './',
    outputDir: 'dist',
    assetsDir: 'static',
    lintOnSave: process.env.NODE_ENV === 'development',
    productionSourceMap: false,
    devServer: {
        port: port,
        open: true,
        overlay: {
            warnings: false,
            errors: true
        },
        proxy: {
            [process.env.VUE_APP_BASE_API]: {
                target: 'http://110.42.224.240:8081/',
                ws: true,
                changeOrigin: true,
                pathRewrite: {['^' + process.env.VUE_APP_BASE_API]: ''}
            }
        }
    },
    lintOnSave: false
}
