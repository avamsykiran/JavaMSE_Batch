import { Component, type ReactNode } from 'react'

interface CounterState {
  count: number
  packets: number
}

class Counter extends Component<{}, CounterState> {
  constructor(props: {}) {
    super(props)
    this.state = {
      count: 0,
      packets: 0,
    }
  }

  componentDidMount(): void {
    this.setState({ count: 1 })
  }

  render(): ReactNode {
    const { count, packets } = this.state

    return (
      <div>
        <button onClick={() => this.changeCount(-1)}>REMOVE</button>
        <strong>
          {count} Items and {packets} Packs
        </strong>
        <button onClick={() => this.changeCount(1)}>ADD</button>
      </div>
    )
  }

  changeCount(delta: number) {
    this.setState((prevState) => {
      let nextCount = prevState.count + delta
      let nextPackets = prevState.packets

      if (nextCount === 10) {
        nextCount = 0
        nextPackets += 1
      } else if (nextCount < 0) {
        if (nextPackets > 0) {
          nextCount = 9
          nextPackets -= 1
        } else {
          nextCount = 0
        }
      }

      return {
        count: nextCount,
        packets: nextPackets,
      }
    })
  }
}

export default Counter
