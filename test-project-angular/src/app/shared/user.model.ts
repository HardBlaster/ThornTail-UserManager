export class UserModel {
  public username: string;
  public hashedPass: string;
  public name: string;
  public role: string;
  public addresses = [];

  constructor() {
  }

}
