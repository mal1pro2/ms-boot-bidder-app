import { ActionItem } from "./actionItem"; 

export class Bid {
    bidId: number;
    maxAutoBidAmount: number;
    bidderName: string; 
    isMeet: boolean;
    isActive: boolean;
    actionItem: ActionItem;
}