# AI Assistance Attribution

## Assignment 6 Part 2 - Yocto Integration

OpenAI Codex/ChatGPT was used as a collaborative programming assistant for
Assignment 6 Part 2 from October 1-8, 2026.

### Full chat history

https://chatgpt.com/s/cx_6ac909686c088191ab5af1c708399afd

### AI-assisted files

- `meta-aesd/recipes-aesd-assignments/aesd-assignments/aesd-assignments_git.bb`
- `meta-aesd/recipes-aesd-assignments/images/core-image-aesd.bb`
- `.github/workflows/github-actions.yml`
- `AI_ATTRIBUTION.md`

The `master+wrynose` BitBake setup, base layers, assignment autotest, SSH-key
action, and initial workflow structure originated from the course-provided
`yocto-assignments-base` repository.

### Assistance provided

AI assistance was used to merge and configure the course's Wrynose starter
content, add the Assignment 3 socket-server repository to the Yocto image,
correct the recipe source directory for Wrynose's unpack layout, and diagnose
the self-hosted GitHub Actions build. The workflow timeout and Docker CPU
limits were adjusted for the resource-constrained runner. The debugging work
also configured persistent download and shared-state caches, preserved
completed build artifacts across host failures, and repaired only stale or
partial BitBake state.

The final failure was traced to changing Docker's CPU allocation after BitBake
had started. This changed the generated `zstd --threads` command from 12 to 10
and caused BitBake's image-task base hashes to change during one invocation.
Starting the container with a fixed CPU set and quota made the metadata
deterministic.

### Student review and verification

The student completed an interactive review and explained in their own words:

- Why the Assignment 3 server must be cross-compiled for the AArch64 target.
- Why socket threads and the data file require graceful shutdown handling.
- How concurrent appends can overwrite or negate another client's changes.
- Why locking must cover both appending a packet and returning the accumulated
  file contents, preventing one client from receiving another client's reply.
- How an unhandled stalled client can eventually cause failure.
- The role of synchronization in coordinating shared state.
- The five-second timing behavior and shutdown signal handling.
- How unchecked buffer growth can corrupt adjacent memory.
- Why an incompatible filesystem image can fail to boot in its target
  environment.

The final GitHub Actions run completed all 6,131 Yocto tasks and the full QEMU
runtime test successfully:

https://github.com/cu-ecen-aeld/assignment-6-sawa9885/actions/runs/37812344783

### External code and sources

No external implementation code and no other student's assignment were used.
Requirements and starter content came from the course Assignment 6
instructions, the instructor's Wrynose/`bitbake-setup` announcement, the
course `yocto-assignments-base` repository, the student's public Assignment 3
repository, and course-provided tests. Instructor guidance about using an
external BitBake shared-state cache was used during build recovery.

### Other student assignments

No other student's assignment was used, supplied to the AI, or referenced.
