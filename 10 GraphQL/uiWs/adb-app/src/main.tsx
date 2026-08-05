import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-icons/font/bootstrap-icons.css'

import './index.css'
import App from './App.tsx'
import ThemeProvider from './lib/context/ThemeProvider.tsx'
import { ApolloProvider } from '@apollo/client/react'
import { apolloClient } from './lib/graphql/apolloClient.ts'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ApolloProvider client={apolloClient}>
      <ThemeProvider>
        <App />
      </ThemeProvider>
    </ApolloProvider>
  </StrictMode>,
)
