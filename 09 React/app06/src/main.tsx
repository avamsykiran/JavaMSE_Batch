import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

import './index.css'
import App from './App.tsx'
import { Provider as ReduxProvider } from 'react-redux'
import { appStore } from './lib/reduxState/appStore.ts'
import ThemeProvider from './lib/context/ThemeProvider.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ReduxProvider store={appStore}>
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </ReduxProvider>
  </StrictMode>,
)
