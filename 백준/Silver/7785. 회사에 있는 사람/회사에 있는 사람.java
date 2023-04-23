import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		HashSet<String> set = new HashSet<>();

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			String name = input[0];
			String log = input[1];

			if (log.equals("enter"))
				set.add(name);
			if (log.equals("leave")) {
				if (set.contains(name))
					set.remove(name);
			}
		}

		List<String> list = new ArrayList<>();
		Iterator<String> it = set.iterator();
		while (it.hasNext())
			list.add(it.next());

		Collections.sort(list, Collections.reverseOrder());

		for (String name : list)
			bw.write(name + "\n");

		bw.flush();

	}

}