import '@testing-library/jest-dom';
import { describe, expect, it } from 'vitest'
import { render, screen } from '@testing-library/react'
import userEvent from '@testing-library/user-event'
import Counter from './Counter'

describe('Counter component', () => {
  it('renders initial counter state', () => {
    render(<Counter />)
    expect(screen.getByText(/Items and/)).toBeInTheDocument()    
  })

  it('increments the counter when ADD is clicked', async () => {
    const user = userEvent.setup()
    render(<Counter />)

    const addButton = screen.getByText('ADD')
    await user.click(addButton)

    expect(screen.getByText('2 Items and 0 Packs')).toBeInTheDocument()
  })

  
  it('wraps count to next packet after 10 adds', async () => {
    const user = userEvent.setup()
    render(<Counter />)

    const addButton = screen.getByText('ADD')
    for (let i = 0; i < 8; i += 1) {
      await user.click(addButton)
    }

    expect(screen.getByText('9 Items and 0 Packs')).toBeInTheDocument()

    await user.click(addButton)
    expect(screen.getByText('0 Items and 1 Packs')).toBeInTheDocument()
  })

  it('decrements count and packet when REMOVE is clicked below 0', async () => {
    const user = userEvent.setup()
    render(<Counter />)

    const addButton = screen.getByText('ADD')
    for (let i = 0; i < 9; i += 1) {
      await user.click(addButton)
    }

    const removeButton = screen.getByText('REMOVE')
    await user.click(removeButton)

    expect(screen.getByText('9 Items and 0 Packs')).toBeInTheDocument()
  }) 
})
