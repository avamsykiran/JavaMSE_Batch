import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>,
)

/*
html
  |-head
    |- ...
  |-body
    |-div#root
      |-App
        |- Compo2
        |- Compo2
        |- Compo3

*/