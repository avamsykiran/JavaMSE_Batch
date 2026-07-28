import { Component, type ReactNode } from "react";

class ItemCount extends Component<{}, { items: number, packets: number }> {
    constructor(props) {
        super(props);
        this.state = {
            items: 0,
            packets: 0   //ten items make one pack
        }
    }

    componentDidMount(): void {
        this.setState({ items: 1 });
    }

    componentDidUpdate(_prevProps: Readonly<{}>, _prevState: Readonly<{ items: number; packets: number; }>, _snapshot?: any): void {
        const { items, packets } = this.state;

        if (items < 0 && packets === 0) {
            this.setState({ items: 0 });
        } else if (items < 0 && packets > 0) {
            this.setState({ items: 9, packets: packets - 1 });
        } else if (items === 10) {
            this.setState({ items: 0, packets: packets + 1 });
        }
    }

    render(): ReactNode {
        const { items, packets } = this.state;

        return (
            <section>
                <h3>Life cycle methods demo</h3>
                <p>
                    Quantity: <strong>{items}</strong> Items and <strong>{packets} packs.</strong>
                </p>
                <button type="button" onClick={_e => this.setState({ items: items - 1 })}> Remove </button>
                <button type="button" onClick={_e => this.setState({ items: items + 1 })}> Add </button>
            </section>
        )
    }
}

export default ItemCount;