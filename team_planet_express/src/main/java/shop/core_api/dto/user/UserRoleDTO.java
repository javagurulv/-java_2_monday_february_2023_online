package shop.core_api.dto.user;

public enum UserRoleDTO {

    GUEST(1, "Guest"),
    CUSTOMER(2, "Customer"),
    MANAGER(4, "Manager"),
    ADMIN(8, "Admin"),
    ALL_USERS(Integer.MAX_VALUE, "GOD");

    private final int role;
    private final String defaultName;

    UserRoleDTO(int role, String defaultName) {
        this.role = role;
        this.defaultName = defaultName;
    }

    public static int getAccessNumber(UserRoleDTO... roles) {
        int result = 0;
        for (UserRoleDTO role : roles) {
            result += role.getRoleNumber();
        }
        return result;
    }

    public static int getAccessNumberExclude(UserRoleDTO... roles) {
        int result = UserRoleDTO.ALL_USERS.getRoleNumber();
        for (UserRoleDTO role : roles) {
            result -= role.getRoleNumber();
        }
        return result;
    }

    public int getRoleNumber() {
        return role;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public boolean checkPermission(int permission) {
        return (permission & this.role) > 0;
    }

}
