interface AddressDetails {
    doorNumber: string;
    city: string;
    state: string;
}
type Address = string | AddressDetails;
declare abstract class Employee {
    private firstName;
    private lastName;
    private address;
    protected basicPay: number;
    constructor(firstName: string, lastName: string, address: Address, basicPay: number);
    abstract netPay(): number;
}
export declare class RegularEmployee extends Employee {
    protected tax: number;
    constructor(firstName: string, lastName: string, address: Address, basicPay: number);
    netPay(): number;
}
export declare class Manager extends RegularEmployee {
    private hra;
    constructor(firstName: string, lastName: string, address: Address, basicPay: number, hra: number);
    netPay(): number;
}
export {};
//# sourceMappingURL=oopDemo.d.ts.map