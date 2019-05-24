package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMCLN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP731";
    String K_SUBOPT_FMT = "BPX01";
    String K_T_MAINT_CLND_INFO = "BP-T-MAINT-CLND-INFO";
    String K_CMP_SET_SBUS_TRN = "SC-SET-SUBS-TRANS";
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "CLEAN PARA MAINTEN";
    String K_HIS_COPYBOOK_NAME = "BPCHCLN";
    String K_CHECK_CPN_EXSIT = "BP-PARM-READ        ";
    String K_PARM_TYPE = "CPN  ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    short WK_7 = 7;
    short WK_10 = 10;
    short WK_31 = 31;
    short WK_50 = 50;
    short WK_99 = 99;
    String WS_CPN_1 = " ";
    short WS_CPN_NO = 0;
    int WS_DATE = 0;
    BPZSMCLN_WS_OUTPUT_DATA WS_OUTPUT_DATA = new BPZSMCLN_WS_OUTPUT_DATA();
    char WS_OUTPUT_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FUNC = ' ';
    char WS_STS_FLG = ' ';
    char WS_SPLIT_FLG = ' ';
    char WS_CLN_FLG_CHK = ' ';
    char WS_CLN_FRY_FLG = ' ';
    char WS_RES_FRY_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCTCLND BPCTCLND = new BPCTCLND();
    BPCMCLNO BPCMCLNO = new BPCMCLNO();
    BPCPQBNK BPCPQBNK = new BPCPQBNK();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRCLND BPRCLND = new BPRCLND();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCHCLN BPCOHCLN = new BPCHCLN();
    BPCHCLN BPCNHCLN = new BPCHCLN();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPRCPN BPRCPN = new BPRCPN();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPCSMCLN BPCSMCLN;
    public void MP(SCCGWA SCCGWA, BPCSMCLN BPCSMCLN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMCLN = BPCSMCLN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-START ");
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B000-END   ");
        CEP.TRC(SCCGWA, "BPZSMCLN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQBNK);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSMCLN.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "B100 START");
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 START ");
        B200_MOVE_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "B200 END");
        if (BPCSMCLN.INFO.FUNC == 'Q'
            || BPCSMCLN.INFO.FUNC == 'A'
            || BPCSMCLN.INFO.FUNC == 'U'
            || BPCSMCLN.INFO.FUNC == 'C'
            || BPCSMCLN.INFO.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMCLN.INFO.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, BPCSMCLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMCLN.INFO.FUNC == 'A' 
            || BPCSMCLN.INFO.FUNC == 'U' 
            || BPCSMCLN.INFO.FUNC == 'D') {
            WS_CLN_FRY_FLG = BPCSMCLN.CLN_FRY;
            if ((WS_CLN_FRY_FLG != 'D' 
                && WS_CLN_FRY_FLG != 'W' 
                && WS_CLN_FRY_FLG != 'T' 
                && WS_CLN_FRY_FLG != 'M' 
                && WS_CLN_FRY_FLG != 'Q' 
                && WS_CLN_FRY_FLG != 'H' 
                && WS_CLN_FRY_FLG != 'Y' 
                && WS_CLN_FRY_FLG != 'S')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMCLN.CLN_CYC == 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMCLN.BNK.equalsIgnoreCase("0")) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.init(SCCGWA, BPCPQBNK);
                BPCPQBNK.DATA_INFO.BNK = BPCSMCLN.BNK;
                S000_CALL_BPZPQBNK();
                if (pgmRtn) return;
            }
            if (WS_CLN_FRY_FLG == 'W') {
                if (BPCSMCLN.CLN_CYC > WK_7) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_CYC_OVER_SUND, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_CLN_FRY_FLG == 'T') {
                if (BPCSMCLN.CLN_CYC > WK_10) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_CYC_OVER_TEN, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_CLN_FRY_FLG == 'M' 
                || WS_CLN_FRY_FLG == 'Q' 
                || WS_CLN_FRY_FLG == 'H') {
                if (BPCSMCLN.CLN_CYC > WK_31) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_CYC_OVER_MONT, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (WS_CLN_FRY_FLG == 'S') {
                WS_DATE = BPCSMCLN.CLN_CYC;
                R000_CHECK_DATE();
                if (pgmRtn) return;
                if (BPCSMCLN.CLN_CYC < SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_RES_FRY_FLG = BPCSMCLN.RES_FRY;
            if ((WS_RES_FRY_FLG != 'D' 
                && WS_RES_FRY_FLG != 'M' 
                && WS_RES_FRY_FLG != 'Y')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_RES_FRY_FLG == 'M') {
                if (BPCSMCLN.RES_CYC > WK_99) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RES_CYC_OVER_99, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                WS_SPLIT_FLG = BPCSMCLN.RES_FLG;
                if ((WS_SPLIT_FLG != 'Y' 
                    && WS_SPLIT_FLG != 'N')) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            if (BPCSMCLN.RES_CYC == 0 
                && WS_RES_FRY_FLG != 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_RES_FRY_FLG == 'Y') {
                if (BPCSMCLN.RES_CYC > WK_50) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RES_CYC_OVER_50, BPCSMCLN.RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            WS_STS_FLG = BPCSMCLN.STS;
            if ((WS_STS_FLG != 'Y' 
                && WS_STS_FLG != 'N')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_CLN_FLG_CHK = BPCSMCLN.CLN_FLG;
            if ((WS_CLN_FLG_CHK != 'Y' 
                && WS_CLN_FLG_CHK != 'N')) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CLN_PAR_INPUT_ERR, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_CLN_FLG_CHK == 'Y') {
                WS_CPN_1 = BPCSMCLN.CPN;
                R000_CHECK_CPN();
                if (pgmRtn) return;
            }
        }
    }
    public void R000_CHECK_CPN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPRCPN);
        BPRPRMT.KEY.CD = WS_CPN_1;
        CEP.TRC(SCCGWA, WS_CPN_1);
        BPRPRMT.KEY.TYP = K_PARM_TYPE;
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "1");
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TX_NOTFND, BPCSMCLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_CHECK_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = WS_DATE;
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            BPCSMCLN.RC.RC_AP = "SC";
            BPCSMCLN.RC.RC_CODE = SCCCKDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B200_MOVE_INPUT_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRCLND);
        CEP.TRC(SCCGWA, BPCSMCLN.BNK);
        BPRCLND.KEY.BNK = BPCSMCLN.BNK;
        BPRCLND.KEY.CLN_ID = BPCSMCLN.CLN_ID;
        BPRCLND.KEY.CLN_RULE = BPCSMCLN.CLN_RULE;
        BPRCLND.DES = BPCSMCLN.DES;
        BPRCLND.STS = BPCSMCLN.STS;
        BPRCLND.CLN_FRY = BPCSMCLN.CLN_FRY;
        BPRCLND.CLN_CYC = BPCSMCLN.CLN_CYC;
        BPRCLND.RES_FRY = BPCSMCLN.RES_FRY;
        BPRCLND.RES_CYC = BPCSMCLN.RES_CYC;
        BPRCLND.RES_FLG = BPCSMCLN.RES_FLG;
        BPRCLND.CLN_FLG = BPCSMCLN.CLN_FLG;
        BPRCLND.CPN = BPCSMCLN.CPN;
        BPRCLND.REMARK = BPCSMCLN.REMARK;
        if (BPCSMCLN.INFO.FUNC == 'U') {
            BPRCLND.UPT_DT = SCCGWA.COMM_AREA.TR_DATE;
            BPRCLND.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRCLND.UPT_BRH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        if (BPCSMCLN.INFO.FUNC == 'A') {
            BPRCLND.UPT_DT = SCCGWA.COMM_AREA.TR_DATE;
            BPRCLND.UPT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRCLND.UPT_BRH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTCLND);
        if (BPCSMCLN.INFO.FUNC == 'Q'
            || BPCSMCLN.INFO.FUNC == 'C') {
            BPCTCLND.INFO.FUNC = 'I';
        } else if (BPCSMCLN.INFO.FUNC == 'A') {
            BPCTCLND.INFO.FUNC = 'A';
        } else if (BPCSMCLN.INFO.FUNC == 'U'
            || BPCSMCLN.INFO.FUNC == 'D') {
            BPCTCLND.INFO.FUNC = 'R';
        }
        S000_CALL_BPZTCLND();
        if (pgmRtn) return;
        R000_CHECK_RETURN_1();
        if (pgmRtn) return;
        if (BPCSMCLN.INFO.FUNC == 'D' 
            || BPCSMCLN.INFO.FUNC == 'A' 
            || BPCSMCLN.INFO.FUNC == 'U') {
            R000_TXN_HIS_PROCESS();
            if (pgmRtn) return;
        }
        if (BPCSMCLN.INFO.FUNC == 'U' 
            || BPCSMCLN.INFO.FUNC == 'D') {
            if (BPCSMCLN.INFO.FUNC == 'D') {
                BPCTCLND.INFO.FUNC = 'D';
                S000_CALL_BPZTCLND();
                if (pgmRtn) return;
            }
            if (BPCSMCLN.INFO.FUNC == 'U') {
                B200_MOVE_INPUT_DATA();
                if (pgmRtn) return;
                BPCTCLND.INFO.FUNC = 'U';
                S000_CALL_BPZTCLND();
                if (pgmRtn) return;
            }
        }
        if (BPCSMCLN.OUTPUT_FLG == 'Y') {
            R000_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
    }
    public void R000_SET_RETURN_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCSUBS);
        CEP.TRC(SCCGWA, BPCSMCLN.SUB_FUNC);
        WS_FUNC = BPCSMCLN.SUB_FUNC;
        if (WS_FUNC == 'I') {
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6103;
        } else if (WS_FUNC == 'M') {
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6105;
        } else if (WS_FUNC == 'D') {
            SCCSUBS.AP_CODE = 999;
            SCCSUBS.TR_CODE = 6106;
        }
        S000_SET_SUBS_TRN();
        if (pgmRtn) return;
    }
    public void S000_SET_SUBS_TRN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_SET_SBUS_TRN, SCCSUBS);
        CEP.TRC(SCCGWA, "S000-END-SUB");
        CEP.TRC(SCCGWA, SCCSUBS.TR_CODE);
    }
    public void B220_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMCLN.BNK);
        CEP.TRC(SCCGWA, BPCSMCLN.CLN_ID);
        CEP.TRC(SCCGWA, BPCSMCLN.CLN_RULE);
        IBS.init(SCCGWA, BPCTCLND);
        BPCTCLND.INFO.FUNC = 'B';
        BPCTCLND.INFO.OPT = 'S';
        S000_CALL_BPZTCLND();
        if (pgmRtn) return;
        if (BPCSMCLN.OUTPUT_FLG == 'Y') {
            WS_OUTPUT_FLG = 'T';
            B221_FMT_OUTPUT_DATA();
            if (pgmRtn) return;
        }
        WS_STOP_FLG = 'N';
        while (WS_STOP_FLG != 'Y' 
            && SCCMPAG.FUNC != 'E') {
            IBS.init(SCCGWA, BPCTCLND);
            BPCTCLND.INFO.FUNC = 'B';
            BPCTCLND.INFO.OPT = 'N';
            S000_CALL_BPZTCLND();
            if (pgmRtn) return;
            if (BPCTCLND.RETURN_INFO == 'N') {
                WS_STOP_FLG = 'Y';
            } else {
                if (BPCSMCLN.OUTPUT_FLG == 'Y') {
                    WS_OUTPUT_FLG = 'D';
                    B221_FMT_OUTPUT_DATA();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPCTCLND);
        BPCTCLND.INFO.FUNC = 'B';
        BPCTCLND.INFO.OPT = 'E';
        S000_CALL_BPZTCLND();
        if (pgmRtn) return;
    }
    public void B221_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        if (WS_OUTPUT_FLG == 'T') {
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'S';
            SCCMPAG.TITL = " ";
            SCCMPAG.SUBT_ROW_CNT = 0;
            SCCMPAG.MAX_COL_NO = 153;
            SCCMPAG.SCR_ROW_CNT = 100;
            SCCMPAG.SCR_COL_CNT = 200;
            B_MPAG();
            if (pgmRtn) return;
        }
        if (WS_OUTPUT_FLG == 'D') {
            IBS.init(SCCGWA, WS_OUTPUT_DATA);
            WS_OUTPUT_DATA.WS_BNK = BPRCLND.KEY.BNK;
            WS_OUTPUT_DATA.WS_CLN_ID = BPRCLND.KEY.CLN_ID;
            WS_OUTPUT_DATA.WS_CLN_RULE = BPRCLND.KEY.CLN_RULE;
            WS_OUTPUT_DATA.WS_DES = BPRCLND.DES;
            WS_OUTPUT_DATA.WS_FLAG = 0X02;
            WS_OUTPUT_DATA.WS_STS = BPRCLND.STS;
            CEP.TRC(SCCGWA, WS_OUTPUT_DATA);
            IBS.init(SCCGWA, SCCMPAG);
            SCCMPAG.FUNC = 'D';
            SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DATA);
            SCCMPAG.DATA_LEN = 153;
            CEP.TRC(SCCGWA, SCCMPAG.DATA_LEN);
            B_MPAG();
            if (pgmRtn) return;
        }
    }
    public void R000_FMT_OUTPUT_DATA() throws IOException,SQLException,Exception {
        R000_OUTPUT_BASIC_DATA();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        if (BPCSMCLN.INFO.FUNC == 'Q') {
            SCCFMT.FMTID = K_SUBOPT_FMT;
        } else {
            SCCFMT.FMTID = K_OUTPUT_FMT;
        }
        SCCFMT.DATA_PTR = BPCMCLNO;
        SCCFMT.DATA_LEN = 331;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_OUTPUT_BASIC_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCMCLNO);
        BPCMCLNO.BNK = BPRCLND.KEY.BNK;
        BPCMCLNO.CLN_ID = BPRCLND.KEY.CLN_ID;
        BPCMCLNO.CLN_RULE = BPRCLND.KEY.CLN_RULE;
        CEP.TRC(SCCGWA, BPCMCLNO.CPN);
        BPCMCLNO.DES = BPRCLND.DES;
        BPCMCLNO.STS = BPRCLND.STS;
        BPCMCLNO.CLN_FRY = BPRCLND.CLN_FRY;
        BPCMCLNO.CLN_CYC = BPRCLND.CLN_CYC;
        BPCMCLNO.RES_FRY = BPRCLND.RES_FRY;
        BPCMCLNO.RES_CYC = BPRCLND.RES_CYC;
        BPCMCLNO.RES_FLG = BPRCLND.RES_FLG;
        BPCMCLNO.CLN_FLG = BPRCLND.CLN_FLG;
        BPCMCLNO.CPN = BPRCLND.CPN;
        CEP.TRC(SCCGWA, BPCMCLNO.CPN);
        BPCMCLNO.REMARK = BPRCLND.REMARK;
        BPCMCLNO.UPT_DT = BPRCLND.UPT_DT;
        BPCMCLNO.UPT_TLR = BPRCLND.UPT_TLR;
        BPCMCLNO.UPT_BRH = BPRCLND.UPT_BRH;
    }
    public void R000_CHECK_RETURN_1() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTCLND.RETURN_INFO);
        if (BPCTCLND.RETURN_INFO == 'N') {
            if (BPCSMCLN.INFO.FUNC == 'Q' 
                || BPCSMCLN.INFO.FUNC == 'U' 
                || BPCSMCLN.INFO.FUNC == 'D') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOT_EXIST, BPCSMCLN.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMCLN.INFO.FUNC == 'C') {
                BPCSMCLN.EXIST_FLG = 'N';
            }
        }
        if (BPCTCLND.RETURN_INFO == 'F') {
            if (BPCSMCLN.INFO.FUNC == 'C') {
                BPCSMCLN.EXIST_FLG = 'Y';
            }
        }
        if (BPCTCLND.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCSMCLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMCLN.INFO.FUNC == 'U') {
            IBS.init(SCCGWA, BPCOHCLN);
            BPCOHCLN.BNK = BPRCLND.KEY.BNK;
            BPCOHCLN.CLN_ID = BPRCLND.KEY.CLN_ID;
            BPCOHCLN.CLN_RULE = BPRCLND.KEY.CLN_RULE;
            BPCOHCLN.DES = BPRCLND.DES;
            BPCOHCLN.STS = BPRCLND.STS;
            BPCOHCLN.CLN_FRY = BPRCLND.CLN_FRY;
            BPCOHCLN.CLN_CYC = BPRCLND.CLN_CYC;
            BPCOHCLN.RES_FRY = BPRCLND.RES_FRY;
            BPCOHCLN.RES_CYC = BPRCLND.RES_CYC;
            BPCOHCLN.RES_FLG = BPRCLND.RES_FLG;
            BPCOHCLN.CLN_FLG = BPRCLND.CLN_FLG;
            BPCOHCLN.CPN = BPRCLND.CPN;
            BPCOHCLN.REMARK = BPRCLND.REMARK;
            BPCOHCLN.UPT_DT = BPRCLND.UPT_DT;
            BPCOHCLN.UPT_TLR = BPRCLND.UPT_TLR;
            BPCOHCLN.UPT_BRH = BPRCLND.UPT_BRH;
        }
    }
    public void R000_TXN_HIS_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        if (BPCSMCLN.INFO.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
        }
        if (BPCSMCLN.INFO.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
        }
        if (BPCSMCLN.INFO.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
        }
        BPCPNHIS.INFO.REF_NO = BPCSMCLN.CLN_ID;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK_NAME;
        if (BPCSMCLN.INFO.FUNC == 'U') {
            BPCPNHIS.INFO.OLD_DAT_PT = BPCOHCLN;
            IBS.init(SCCGWA, BPCNHCLN);
            BPCNHCLN.BNK = BPCSMCLN.BNK;
            BPCNHCLN.CLN_ID = BPCSMCLN.CLN_ID;
            BPCNHCLN.CLN_RULE = BPCSMCLN.CLN_RULE;
            BPCNHCLN.DES = BPCSMCLN.DES;
            BPCNHCLN.STS = BPCSMCLN.STS;
            BPCNHCLN.CLN_FRY = BPCSMCLN.CLN_FRY;
            BPCNHCLN.CLN_CYC = BPCSMCLN.CLN_CYC;
            BPCNHCLN.RES_FRY = BPCSMCLN.RES_FRY;
            BPCNHCLN.RES_CYC = BPCSMCLN.RES_CYC;
            BPCNHCLN.RES_FLG = BPCSMCLN.RES_FLG;
            BPCNHCLN.CLN_FLG = BPCSMCLN.CLN_FLG;
            BPCNHCLN.CPN = BPCSMCLN.CPN;
            BPCNHCLN.REMARK = BPCSMCLN.REMARK;
            BPCNHCLN.UPT_DT = BPCSMCLN.UPT_DT;
            BPCNHCLN.UPT_TLR = BPCSMCLN.UPT_TLR;
            BPCNHCLN.UPT_BRH = BPCSMCLN.UPT_BRH;
            BPCPNHIS.INFO.NEW_DAT_PT = BPCNHCLN;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CMP_TXN_HIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMCLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTCLND() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRCLND.KEY.BNK);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_ID);
        CEP.TRC(SCCGWA, BPRCLND.KEY.CLN_RULE);
        BPCTCLND.INFO.POINTER = BPRCLND;
        BPCTCLND.LEN = 329;
        IBS.CALLCPN(SCCGWA, K_T_MAINT_CLND_INFO, BPCTCLND);
        if (BPCTCLND.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTCLND.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMCLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CHECK_CPN_EXSIT, BPCPRMR);
    }
    public void S000_CALL_BPZPQBNK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-QUERY-BANK", BPCPQBNK);
        if (BPCPQBNK.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBNK.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSMCLN.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSMCLN.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
