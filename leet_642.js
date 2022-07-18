TC - O(N^2)
SC - O(N)
class AutocompleteSystem {
	constructor(sentences, times) {
		this.root = {};
		this.prefix = '';

		for (let i = 0; i < sentences.length; i++) {
			this.root[sentences[i]] = times[i];
		}
	}

	input(c) {
		if (c === '#') {
			this.root[this.prefix] = this.root[this.prefix] + 1 || 1;
			this.prefix = '';
			return [];
		}

		this.prefix += c;

		
		const words = Object.entries(this.root);
		const searches = words.filter(([s]) => s.startsWith(this.prefix));

		
		searches.sort(([s1, t1], [s2, t2]) => {
			if (t1 === t2) return s1.localeCompare(s2);

			return t2 - t1;
		});

		
		return searches.slice(0, 3).map(([s]) => s);
	}
}