package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZCMPCY {
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    String K_CALL_CPN_NAME = "BP-INQUIRE-CCY     ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCGWA SCCGWA;
    BPCMPCY BPCMPCY;
    public void MP(SCCGWA SCCGWA, BPCMPCY BPCMPCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMPCY = BPCMPCY;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZCMPCY return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCMPCY.RC.APP = SCCGWA.COMM_AREA.AP_MMO;
        BPCMPCY.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B200_QUERY_CCY_INFOR();
        if (pgmRtn) return;
        B300_AMT_TRANS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCMPCY.I_CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_SPACE_ERR, BPCMPCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCMPCY.I_FLG != '0') 
            && (BPCMPCY.I_FLG != '1')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_MPCY_FLG_ERR, BPCMPCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((BPCMPCY.I_AMT <= 0)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AMT_MUST_GT_ZERO, BPCMPCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_QUERY_CCY_INFOR() throws IOException,SQLException,Exception {
        R000_TRANS_DATA_PARAM();
        if (pgmRtn) return;
        S000_CALL_BPZMCCY();
        if (pgmRtn) return;
    }
    public void B300_AMT_TRANS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCMPCY.I_FLG);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.CASH_MTH);
        CEP.TRC(SCCGWA, BPCQCCY.DATA.RND_MTH);
        if (BPCMPCY.I_FLG == '0') {
            BPCQCCY.DATA.RND_MTH = '0';
            if (BPCQCCY.DATA.CASH_MTH == '0') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 15 - 1) + "0000" + JIBS_NumStr.substring(15 + 4 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(15 - 1, 15 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += 1;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 15 - 1) + "0000" + JIBS_NumStr.substring(15 + 4 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .5;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 15 - 1) + "0000" + JIBS_NumStr.substring(15 + 4 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            } else if (BPCQCCY.DATA.CASH_MTH == '1') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + "000" + JIBS_NumStr.substring(16 + 3 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += .1;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + "000" + JIBS_NumStr.substring(16 + 3 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .05;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + "000" + JIBS_NumStr.substring(16 + 3 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            } else if (BPCQCCY.DATA.CASH_MTH == '2') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 17 - 1) + "00" + JIBS_NumStr.substring(17 + 2 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += .01;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 17 - 1) + "00" + JIBS_NumStr.substring(17 + 2 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .005;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 17 - 1) + "00" + JIBS_NumStr.substring(17 + 2 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            } else if (BPCQCCY.DATA.CASH_MTH == '3') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 18 - 1) + "0" + JIBS_NumStr.substring(18 + 1 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += .001;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 18 - 1) + "0" + JIBS_NumStr.substring(18 + 1 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .0005;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 18 - 1) + "0" + JIBS_NumStr.substring(18 + 1 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            }
        } else {
            if (BPCQCCY.DATA.DEC_MTH == '0') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 15 - 1) + "0000" + JIBS_NumStr.substring(15 + 4 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(15 - 1, 15 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += 1;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 15 - 1) + "0000" + JIBS_NumStr.substring(15 + 4 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .5;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 15 - 1) + "0000" + JIBS_NumStr.substring(15 + 4 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            } else if (BPCQCCY.DATA.DEC_MTH == '1') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + "000" + JIBS_NumStr.substring(16 + 3 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(16 - 1, 16 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += .1;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + "000" + JIBS_NumStr.substring(16 + 3 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .05;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 16 - 1) + "000" + JIBS_NumStr.substring(16 + 3 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            } else if (BPCQCCY.DATA.DEC_MTH == '2') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 17 - 1) + "00" + JIBS_NumStr.substring(17 + 2 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(17 - 1, 17 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += .01;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 17 - 1) + "00" + JIBS_NumStr.substring(17 + 2 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .005;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 17 - 1) + "00" + JIBS_NumStr.substring(17 + 2 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            } else if (BPCQCCY.DATA.DEC_MTH == '3') {
                if (BPCQCCY.DATA.RND_MTH == '0') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 18 - 1) + "0" + JIBS_NumStr.substring(18 + 1 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '1') {
                    BPCMPCY.O_AMT = BPCMPCY.I_AMT;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_tmp_int = JIBS_tmp_str[0].length();
                    for (int i=0;i<18-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
                    if (!JIBS_tmp_str[0].substring(18 - 1, 18 + 1 - 1).equalsIgnoreCase("0")) {
                        BPCMPCY.O_AMT += .001;
                    }
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 18 - 1) + "0" + JIBS_NumStr.substring(18 + 1 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                } else if (BPCQCCY.DATA.RND_MTH == '2') {
                    BPCMPCY.I_AMT += .0005;
                    JIBS_tmp_str[0] = "" + BPCMPCY.O_AMT;
                    JIBS_f0 = "";
                    for (int i=0;i<18-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
                    JIBS_NumStr = JIBS_f0 + BPCMPCY.O_AMT;
                    JIBS_NumStr = JIBS_NumStr.substring(0, 18 - 1) + "0" + JIBS_NumStr.substring(18 + 1 - 1);
                    BPCMPCY.O_AMT = Double.parseDouble(JIBS_NumStr);
                }
            }
        }
    }
    public void R000_TRANS_DATA_PARAM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = BPCMPCY.I_CCY;
    }
    public void S000_CALL_BPZMCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_CPN_NAME, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_REC_NOTFND, BPCMPCY.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCMPCY.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCMPCY = ");
            CEP.TRC(SCCGWA, BPCMPCY);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
