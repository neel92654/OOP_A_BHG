public class ChatFilter {

    public static String filter(String[] lines, String keyword) {
        int count = 0;
        StringBuilder report = new StringBuilder();

        for (String line : lines) {
            String[] parts = line.split(" ", 3);

            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (message.toLowerCase().contains(keyword.toLowerCase())) {
                count++;
                report.append(time).append(" ").append(user).append(": ").append(message).append("\n");
            }
        }

        System.out.println("Matches: " + count);
        System.out.print(report.toString());

        return report.toString();
    }

    public static void main(String[] args) {
        String[] lines = {
            "10:05 alice Hello there",
            "10:06 bob How are you?",
            "10:07"
        };

        String keyword = "hello";
        System.out.println("Filtering for keyword: " + keyword);
        filter(lines, keyword);
    }
}
