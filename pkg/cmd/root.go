package cmd

import (
	"os"
	"github.com/spf13/cobra"
)

var rootCmd = &cobra.Command{
	Use: "yquest",
	Short: "",
	Long: "",
}

func Execute(){
	error := rootCmd.Execute()

	if error != nil {
		os.Exit(1)
	}
}

func init(){
	rootCmd.AddCommand(NewExecuteCmd())
}