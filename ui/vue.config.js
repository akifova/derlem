module.exports = {
  publicPath: process.env.ACTIVE_PROFILE,
  pluginOptions: {
    i18n: {
      locale: 'tr',
      fallbackLocale: 'tr',
      localeDir: '/app/common/',
      enableInSFC: true,
      enableLegacy: true,
      runtimeOnly: false,
      compositionOnly: true,
      fullInstall: true
    }
  },
  devServer: {
    //proxy: 'http://37.247.105.22/',
    proxy: 'http://localhost:8081',
  }
};
