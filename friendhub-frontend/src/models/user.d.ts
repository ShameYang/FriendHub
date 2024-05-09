/**
 * 用户类别
 */
export type UserType = {
    id: number;
    userName: string;
    userAccount: string;
    avatarUrl?: string;
    gender: number;
    phone: string;
    email: string;
    userStatus: number;
    createTime: Date;
    userRole: number;
    tags: string;
    profile: string;
};
