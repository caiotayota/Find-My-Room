export type Ad = {
    id: number;
    room: {
        id: number;
        streetAddress: string;
        eirCode: string;
        roomType: string;
        ensuiteBathroom: string;
        heating: string;
        carpeted: string;
    };
    user: {
        id: number;
        email: string;
        firstName: string;
        lastName: String;
        phoneNo: string;

    };
    rent: number;
    billsIncluded: string;
    parking: string;
    owner_occupied: string;
    petAllowed: string;
    washingMachine: string;
    dryer: string;
    dishWasher: string;
    createdAt: string;
}