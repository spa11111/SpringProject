      // Sample chapters data
        const chaptersData = [
            { id: 1, title: "Chapter 1: The Beginning", status: "published", wordCount: 2847, lastEdited: "2 hours ago" },
            { id: 2, title: "Chapter 2: Through the Portal", status: "published", wordCount: 3156, lastEdited: "1 day ago" },
            { id: 3, title: "Chapter 3: First Encounters", status: "published", wordCount: 2934, lastEdited: "3 days ago" },
            { id: 4, title: "Chapter 4: The Guardian's Test", status: "draft", wordCount: 1823, lastEdited: "5 days ago" },
            { id: 5, title: "Chapter 5: Allies and Enemies", status: "draft", wordCount: 0, lastEdited: "Never" },
        ];

        let currentChapter = 1;
        let autoSaveTimer;
        let wordCount = 247;

        // Initialize the application
        document.addEventListener('DOMContentLoaded', function() {
            populateChapterList();
            initializeEditor();
            initializeToolbar();
            initializeSidebar();
            startAutoSave();
            updateWordCount();
        });

        // Populate chapter list
        function populateChapterList() {
            const container = document.getElementById('chapterList');
            container.innerHTML = '';

            chaptersData.forEach(chapter => {
                const chapterElement = document.createElement('div');
                chapterElement.className = `chapter-item ${chapter.status} ${chapter.id === currentChapter ? 'active' : ''}`;
                chapterElement.dataset.chapterId = chapter.id;
                
                chapterElement.innerHTML = `
                    <div class="d-flex justify-content-between align-items-start">
                        <div class="flex-grow-1">
                            <h6 class="mb-1">${chapter.title}</h6>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-muted">${chapter.wordCount} words</small>
                                <span class="badge ${chapter.status === 'published' ? 'bg-secondary' : 'bg-secondary'} badge-sm">
                                    ${chapter.status}
                                </span>
                            </div>
                            <small class="text-muted">Last edited: ${chapter.lastEdited}</small>
                        </div>
                        <div class="chapter-actions">
                            <button class="action-btn" title="Edit" onclick="editChapter(${chapter.id})">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="action-btn" title="Delete" onclick="deleteChapter(${chapter.id})">
                                <i class="fas fa-trash"></i>
                            </button>
                            <button class="action-btn" title="More" onclick="showChapterMenu(${chapter.id})">
                                <i class="fas fa-ellipsis-v"></i>
                            </button>
                        </div>
                    </div>
                `;

                chapterElement.addEventListener('click', function(e) {
                    if (!e.target.closest('.chapter-actions')) {
                        selectChapter(chapter.id);
                    }
                });

                container.appendChild(chapterElement);
            });
        }

        // Select chapter
        function selectChapter(chapterId) {
            currentChapter = chapterId;
            
            // Update active state
            document.querySelectorAll('.chapter-item').forEach(item => {
                item.classList.remove('active');
            });
            document.querySelector(`[data-chapter-id="${chapterId}"]`).classList.add('active');

            // Load chapter content (simulate)
            const chapter = chaptersData.find(c => c.id === chapterId);
            if (chapter) {
                document.getElementById('chapterTitle').value = chapter.title;
                // In a real app, you would load the chapter content here
            }
        }

        // Initialize editor
        function initializeEditor() {
            const editor = document.getElementById('storyEditor');
            const titleInput = document.getElementById('chapterTitle');

            // Add placeholder behavior
            function updatePlaceholder() {
                if (editor.textContent.trim() === '') {
                    editor.classList.add('empty');
                } else {
                    editor.classList.remove('empty');
                }
            }

            editor.addEventListener('input', function() {
                updatePlaceholder();
                updateWordCount();
                showSaveIndicator('saving');
                clearTimeout(autoSaveTimer);
                autoSaveTimer = setTimeout(() => {
                    saveContent();
                }, 2000);
            });

            titleInput.addEventListener('input', function() {
                showSaveIndicator('saving');
                clearTimeout(autoSaveTimer);
                autoSaveTimer = setTimeout(() => {
                    saveContent();
                }, 2000);
            });

            updatePlaceholder();
        }

        // Initialize toolbar
        function initializeToolbar() {
            const toolbarButtons = document.querySelectorAll('.toolbar-btn[data-command]');
            
            toolbarButtons.forEach(button => {
                button.addEventListener('click', function(e) {
                    e.preventDefault();
                    const command = this.dataset.command;
                    document.execCommand(command, false, null);
                    this.classList.toggle('active');
                });
            });

            // Font size selector
            document.getElementById('fontSizeSelect').addEventListener('change', function() {
                document.getElementById('storyEditor').style.fontSize = this.value;
            });

            // Undo/Redo buttons
            document.getElementById('undoBtn').addEventListener('click', function() {
                document.execCommand('undo', false, null);
            });

            document.getElementById('redoBtn').addEventListener('click', function() {
                document.execCommand('redo', false, null);
            });
        }

        // Initialize sidebar
        function initializeSidebar() {
            const toggleBtn = document.getElementById('toggleSidebar');
            const sidebar = document.getElementById('sidebar');
            const mainContent = document.getElementById('mainContent');

            toggleBtn.addEventListener('click', function() {
                sidebar.classList.toggle('collapsed');
                mainContent.classList.toggle('expanded');
                
                // Update icon
                const icon = this.querySelector('i');
                if (sidebar.classList.contains('collapsed')) {
                    icon.className = 'fas fa-bars';
                } else {
                    icon.className = 'fas fa-times';
                }
            });

            // Add chapter button
            document.getElementById('addChapterBtn').addEventListener('click', function() {
                addNewChapter();
            });
        }

        // Update word count
        function updateWordCount() {
            const editor = document.getElementById('storyEditor');
            const text = editor.textContent || editor.innerText || '';
            const words = text.trim().split(/\s+/).filter(word => word.length > 0);
            wordCount = words.length;
            
            document.getElementById('wordCountNumber').textContent = wordCount;
        }

        // Show save indicator
        function showSaveIndicator(status) {
            const indicator = document.getElementById('saveIndicator');
            indicator.className = `save-indicator ${status}`;
            
            switch(status) {
                case 'saving':
                    indicator.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Saving...';
                    break;
                case 'saved':
                    indicator.innerHTML = '<i class="fas fa-check me-2"></i>All changes saved';
                    break;
                case 'error':
                    indicator.innerHTML = '<i class="fas fa-exclamation-triangle me-2"></i>Save failed';
                    break;
            }
        }

        // Save content
        function saveContent() {
            // Simulate API call
            setTimeout(() => {
                showSaveIndicator('saved');
                
                // Update chapter data
                const chapter = chaptersData.find(c => c.id === currentChapter);
                if (chapter) {
                    chapter.title = document.getElementById('chapterTitle').value;
                    chapter.wordCount = wordCount;
                    chapter.lastEdited = 'Just now';
                }
                
                populateChapterList();
            }, 1000);
        }

        // Start auto-save
        function startAutoSave() {
            setInterval(() => {
                if (document.getElementById('storyEditor').textContent.trim() !== '') {
                    saveContent();
                }
            }, 30000); // Auto-save every 30 seconds
        }

        // Add new chapter
        function addNewChapter() {
            const newChapter = {
                id: chaptersData.length + 1,
                title: `Chapter ${chaptersData.length + 1}: Untitled`,
                status: 'draft',
                wordCount: 0,
                lastEdited: 'Never'
            };
            
            chaptersData.push(newChapter);
            populateChapterList();
            selectChapter(newChapter.id);
            
            // Clear editor
            document.getElementById('storyEditor').innerHTML = '';
            document.getElementById('chapterTitle').value = newChapter.title;
            document.getElementById('chapterTitle').select();
        }

        // Edit chapter
        function editChapter(chapterId) {
            selectChapter(chapterId);
        }

        // Delete chapter
        function deleteChapter(chapterId) {
            if (confirm('Are you sure you want to delete this chapter? This action cannot be undone.')) {
                const index = chaptersData.findIndex(c => c.id === chapterId);
                if (index > -1) {
                    chaptersData.splice(index, 1);
                    populateChapterList();
                    
                    // Select first chapter if current was deleted
                    if (currentChapter === chapterId && chaptersData.length > 0) {
                        selectChapter(chaptersData[0].id);
                    }
                }
            }
        }

        // Show chapter menu
        function showChapterMenu(chapterId) {
            // Implement context menu functionality
            console.log('Show menu for chapter:', chapterId);
        }

        // Preview functionality
        document.getElementById('previewBtn').addEventListener('click', function() {
            const content = document.getElementById('storyEditor').innerHTML;
            const title = document.getElementById('chapterTitle').value;
            
            // Open preview in new window
            const previewWindow = window.open('', '_blank');
            previewWindow.document.write(`
                <html>
                    <head>
                        <title>${title}</title>
                        <style>
                            body { font-family: 'Merriweather', serif; max-width: 800px; margin: 0 auto; padding: 40px 20px; line-height: 1.8; }
                            h1 { color: #333; border-bottom: 2px solid #667eea; padding-bottom: 10px; }
                        </style>
                    </head>
                    <body>
                        <h1>${title}</h1>
                        ${content}
                    </body>
                </html>
            `);
        });

        // Publish functionality
        document.getElementById('confirmPublish').addEventListener('click', function() {
            // Simulate publishing
            const chapter = chaptersData.find(c => c.id === currentChapter);
            if (chapter) {
                chapter.status = 'published';
                populateChapterList();
                
                // Close modal
                const modal = bootstrap.Modal.getInstance(document.getElementById('publishModal'));
                modal.hide();
                
                // Show success message
                alert('Chapter published successfully!');
            }
        });

        // Keyboard shortcuts
        document.addEventListener('keydown', function(e) {
            if (e.ctrlKey || e.metaKey) {
                switch(e.key) {
                    case 's':
                        e.preventDefault();
                        saveContent();
                        break;
                    case 'b':
                        e.preventDefault();
                        document.execCommand('bold', false, null);
                        break;
                    case 'i':
                        e.preventDefault();
                        document.execCommand('italic', false, null);
                        break;
                    case 'u':
                        e.preventDefault();
                        document.execCommand('underline', false, null);
                        break;
                }
            }
        });

        // Mobile responsiveness
        function handleMobileView() {
            if (window.innerWidth <= 768) {
                document.getElementById('sidebar').classList.add('collapsed');
                document.getElementById('mainContent').classList.add('expanded');
            }
        }

        window.addEventListener('resize', handleMobileView);
        handleMobileView();