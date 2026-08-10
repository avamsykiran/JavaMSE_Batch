import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    // Explicitly define where Vitest should look for tests
    include: ['src/**/*.{test,spec}.{js,mjs,cjs,ts,mts,cts,jsx,tsx}'],
    // Ensure node_modules and build dirs are excluded
    exclude: ['node_modules', 'dist', '.idea', '.git', '.cache'],
  },
})
