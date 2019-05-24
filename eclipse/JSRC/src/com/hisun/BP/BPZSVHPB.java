package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSVHPB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_TBL_FARM = "BPTCNGL ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_R_BRW_TBVD = "BP-R-BRW-TBVD ";
    String CPN_R_ADW_TLSB = "BP-R-ADW-TLSB       ";
    String WS_ERR_MSG = " ";
    int WS_REC_LEN = 0;
    int WS_CNT = 0;
    BPZSVHPB_WS_LINE_VAR WS_LINE_VAR = new BPZSVHPB_WS_LINE_VAR();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRVHPB BPRVHPB = new BPRVHPB();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCOVHPB BPCOVHPB = new BPCOVHPB();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPCRTBDB BPCRTBDB = new BPCRTBDB();
    BPCRTLSB BPCRTLSB = new BPCRTLSB();
    BPRTLSC BPRTLSC = new BPRTLSC();
    SCCGWA SCCGWA;
    BPCSVHPB BPCSVHPB;
    public void MP(SCCGWA SCCGWA, BPCSVHPB BPCSVHPB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSVHPB = BPCSVHPB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSVHPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        SCCGWA.COMM_AREA.DBIO_FLG = ' ';
        IBS.init(SCCGWA, BPCRTLSB);
        IBS.init(SCCGWA, BPRTLSC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSVHPB.INFO.FUNC);
        B010_01_COMMON_CHECK();
        if (pgmRtn) return;
        if (BPCSVHPB.INFO.FUNC == 'B') {
            B010_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCSVHPB.INFO.FUNC == 'A') {
            B020_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCSVHPB.INFO.FUNC == 'M') {
            B030_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (BPCSVHPB.INFO.FUNC == 'D') {
            B040_DELETE_PROC();
            if (pgmRtn) return;
        } else if (BPCSVHPB.INFO.FUNC == 'I') {
            B050_INQUIRE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR, BPCSVHPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSVHPB.INFO.FUNC != 'B') {
            B090_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_01_COMMON_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "COMMON-CHECK");
        if (BPCSVHPB.INFO.CUR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID) 
            && (BPCSVHPB.INFO.FUNC == 'M' 
            || BPCSVHPB.INFO.FUNC == 'A' 
            || BPCSVHPB.INFO.FUNC == 'D')) {
            CEP.TRC(SCCGWA, "AAAAAA");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MAINT_ONESELF;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSVHPB.INFO.CUR_TLR);
        if ((BPCSVHPB.INFO.FUNC == 'M' 
            || BPCSVHPB.INFO.FUNC == 'A') 
            && BPCSVHPB.INFO.CUR_TLR.trim().length() > 0) {
            CEP.TRC(SCCGWA, "BBBBBBBBB");
            IBS.init(SCCGWA, BPCFTLRQ);
            BPCFTLRQ.INFO.TLR = BPCSVHPB.INFO.CUR_TLR;
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
            JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
            if ((BPCSVHPB.INFO.PL_IND == '0' 
                && BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("0")) 
                || (BPCSVHPB.INFO.PL_IND == '1' 
                && BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("0"))) {
                CEP.TRC(SCCGWA, BPCSVHPB.INFO.PL_IND);
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_VLT_TLR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (BPCSVHPB.INFO.CUR_TLR.trim().length() > 0) {
            if (BPCSVHPB.INFO.FUNC == 'A') {
                CEP.TRC(SCCGWA, "CCCCCCCC");
                if (BPCSVHPB.INFO.PL_IND == '0') {
                    CEP.TRC(SCCGWA, "DDDDDDD");
                    IBS.init(SCCGWA, BPRVHPB);
                    IBS.init(SCCGWA, BPCRVHPB);
                    BPRVHPB.BR = BPCSVHPB.INFO.BR;
                    BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
                    BPRVHPB.POLL_BOX_IND = BPCSVHPB.INFO.PL_IND;
                    BPRVHPB.RELATE_FLG = 'Y';
                    BPRVHPB.STS = 'N';
                    BPCRVHPB.INFO.FUNC = 'F';
                    CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    S000_CALL_BPZRVHPB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
                    CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    if (BPCRVHPB.RETURN_INFO == 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_BVB_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (BPCSVHPB.INFO.PL_IND == '1') {
                    CEP.TRC(SCCGWA, "EEEEEEE");
                    IBS.init(SCCGWA, BPRVHPB);
                    IBS.init(SCCGWA, BPCRVHPB);
                    BPRVHPB.BR = BPCSVHPB.INFO.BR;
                    BPRVHPB.POLL_BOX_IND = BPCSVHPB.INFO.PL_IND;
                    BPRVHPB.STS = 'N';
                    BPCRVHPB.INFO.FUNC = 'F';
                    S000_CALL_BPZRVHPB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    if (BPCRVHPB.RETURN_INFO == 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BRP_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    IBS.init(SCCGWA, BPRVHPB);
                    IBS.init(SCCGWA, BPCRVHPB);
                    BPRVHPB.BR = BPCSVHPB.INFO.BR;
                    BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
                    BPRVHPB.POLL_BOX_IND = BPCSVHPB.INFO.PL_IND;
                    BPRVHPB.RELATE_FLG = 'Y';
                    BPRVHPB.STS = 'N';
                    BPCRVHPB.INFO.FUNC = 'F';
                    S000_CALL_BPZRVHPB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    CEP.TRC(SCCGWA, BPCSVHPB.INFO.CUR_TLR);
                    CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
                    if (BPCRVHPB.RETURN_INFO == 'F') {
                        if (BPCSVHPB.INFO.CUR_TLR.equalsIgnoreCase(BPRVHPB.CUR_TLR)) {
                            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_TVL_EXIST;
                            S000_ERR_MSG_PROC();
                            if (pgmRtn) return;
                        }
                    }
                }
            }
        }
        if (BPCSVHPB.INFO.FUNC == 'M') {
            CEP.TRC(SCCGWA, "FFFFFFF");
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
            BPCRVHPB.INFO.FUNC = 'Q';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            if (BPRVHPB.BV_CHK_FLG == 'N' 
                && BPRVHPB.POLL_BOX_IND == '0') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BCHK_FLG_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRVHPB.BV_CHK_FLG == 'N' 
                && BPRVHPB.POLL_BOX_IND == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_VCHK_FLG_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_LINE_VAR.WS_L_POLL_BOX_IND = BPRVHPB.POLL_BOX_IND;
            if (!BPRVHPB.CUR_TLR.equalsIgnoreCase(BPCSVHPB.INFO.CUR_TLR) 
                && BPCSVHPB.INFO.CUR_TLR.trim().length() > 0) {
                if (WS_LINE_VAR.WS_L_POLL_BOX_IND == '0') {
                    CEP.TRC(SCCGWA, "HHHHHH");
                    IBS.init(SCCGWA, BPRVHPB);
                    IBS.init(SCCGWA, BPCRVHPB);
                    BPRVHPB.BR = BPCSVHPB.INFO.BR;
                    BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
                    BPRVHPB.POLL_BOX_IND = WS_LINE_VAR.WS_L_POLL_BOX_IND;
                    BPRVHPB.RELATE_FLG = 'Y';
                    BPRVHPB.STS = 'N';
                    BPCRVHPB.INFO.FUNC = 'F';
                    S000_CALL_BPZRVHPB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    if (BPCRVHPB.RETURN_INFO == 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BOX_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (WS_LINE_VAR.WS_L_POLL_BOX_IND == '1') {
                    CEP.TRC(SCCGWA, "GGGGGG");
                    IBS.init(SCCGWA, BPRVHPB);
                    IBS.init(SCCGWA, BPCRVHPB);
                    BPRVHPB.BR = BPCSVHPB.INFO.BR;
                    BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
                    BPRVHPB.POLL_BOX_IND = WS_LINE_VAR.WS_L_POLL_BOX_IND;
                    BPRVHPB.RELATE_FLG = 'Y';
                    BPRVHPB.STS = 'N';
                    BPCRVHPB.INFO.FUNC = 'F';
                    S000_CALL_BPZRVHPB();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
                    CEP.TRC(SCCGWA, BPCSVHPB.INFO.CUR_TLR);
                    CEP.TRC(SCCGWA, BPRVHPB.CUR_TLR);
                    if (BPCRVHPB.RETURN_INFO == 'F') {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_TVL_EXIST;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            }
        }
        if (BPCSVHPB.INFO.FUNC == 'D') {
            CEP.TRC(SCCGWA, "IIIIIIII");
            IBS.init(SCCGWA, BPRTBVD);
            IBS.init(SCCGWA, BPCRTBDB);
            BPRTBVD.KEY.BR = BPCSVHPB.INFO.BR;
            BPRTBVD.KEY.PL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
            BPRTBVD.KEY.STS = ALL.charAt(0);
            BPRTBVD.TYPE = ALL.charAt(0);
            BPRTBVD.KEY.BV_CODE = "%%%%%%";
            BPCRTBDB.INFO.FUNC = 'G';
            BPCRTBDB.INFO.POINTER = BPRTBVD;
            BPCRTBDB.INFO.LEN = 160;
            S000_CALL_BPZRTBDB();
            if (pgmRtn) return;
            BPCRTBDB.INFO.FUNC = 'N';
            BPCRTBDB.INFO.POINTER = BPRTBVD;
            BPCRTBDB.INFO.LEN = 160;
            S000_CALL_BPZRTBDB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRTBDB.RC.RC_CODE);
            for (WS_CNT = 1; BPCRTBDB.RETURN_INFO != 'N' 
                && WS_CNT <= 5000; WS_CNT += 1) {
                if (BPRTBVD.KEY.STS == '0' 
                    || BPRTBVD.KEY.STS == '1' 
                    || BPRTBVD.KEY.STS == '5') {
                    CEP.TRC(SCCGWA, "CAN NOT DELETE VLT/BOX PLBOX");
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BOX_HAD_BV;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, BPRTBVD.KEY.STS);
                BPCRTBDB.INFO.FUNC = 'N';
                S000_CALL_BPZRTBDB();
                if (pgmRtn) return;
            }
            BPCRTBDB.INFO.FUNC = 'E';
            BPCRTBDB.INFO.POINTER = BPRTBVD;
            BPCRTBDB.INFO.LEN = 160;
            S000_CALL_BPZRTBDB();
            if (pgmRtn) return;
            BPCRTLSB.FUNC = 'B';
            BPRTLSC.KEY.BR = BPCSVHPB.INFO.BR;
            BPRTLSC.KEY.PL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
            BPRTLSC.KEY.CODE_NO = "%%%%%%%%%%%%%%%%%%%%";
            BPRTLSC.SC_TYPE = ALL.charAt(0);
            BPRTLSC.SC_STS = ALL.charAt(0);
            S000_CALL_BPZRTLSB();
            if (pgmRtn) return;
            BPCRTLSB.FUNC = 'R';
            S000_CALL_BPZRTLSB();
            if (pgmRtn) return;
            if (BPCRTLSB.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BOX_HAD_BV;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCRTLSB.FUNC = 'E';
            S000_CALL_BPZRTLSB();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPRVHPB);
            IBS.init(SCCGWA, BPCRVHPB);
            BPRVHPB.KEY.POOL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
            BPCRVHPB.INFO.FUNC = 'Q';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
            if (BPRVHPB.CUR_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                CEP.TRC(SCCGWA, "AAAAAA");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_MAINT_ONESELF;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRVHPB.BV_CHK_FLG == 'N' 
                && BPRVHPB.POLL_BOX_IND == '0') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_BCHK_FLG_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPRVHPB.BV_CHK_FLG == 'N' 
                && BPRVHPB.POLL_BOX_IND == '1') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_VCHK_FLG_NO;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
        BPRVHPB.BR = BPCSVHPB.INFO.BR;
        BPRVHPB.POLL_BOX_IND = BPCSVHPB.INFO.PL_IND;
        BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
        BPRVHPB.RELATE_FLG = BPCSVHPB.INFO.REL_FLG;
        BPRVHPB.STS = BPCSVHPB.INFO.STS;
        if (BPCSVHPB.INFO.BIND_TYP == ' ') {
            BPRVHPB.BIND_TYPE = ALL.charAt(0);
        } else {
            BPRVHPB.BIND_TYPE = BPCSVHPB.INFO.BIND_TYP;
        }
        CEP.TRC(SCCGWA, BPRVHPB.BIND_TYPE);
        BPCRVHPB.INFO.FUNC = 'B';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        BPCRVHPB.INFO.FUNC = 'N';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        B010_LIST_OUTPUT_TITLE();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCRVHPB.RETURN_INFO != 'N' 
            && WS_CNT <= 5000 
            && SCCMPAG.FUNC != 'E'; WS_CNT += 1) {
            B010_LIST_TRANS_DATA();
            if (pgmRtn) return;
            BPCRVHPB.INFO.FUNC = 'N';
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
        }
        BPCRVHPB.INFO.FUNC = 'E';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSVHPB.RC);
    }
    public void B020_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        B020_01_GET_POOL_SEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        if (BPRVHPB.KEY.POOL_BOX_NO == null) BPRVHPB.KEY.POOL_BOX_NO = "";
        JIBS_tmp_int = BPRVHPB.KEY.POOL_BOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRVHPB.KEY.POOL_BOX_NO += " ";
        JIBS_tmp_str[0] = "" + BPCSVHPB.INFO.PL_IND;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<1-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        BPRVHPB.KEY.POOL_BOX_NO = JIBS_tmp_str[0] + BPRVHPB.KEY.POOL_BOX_NO.substring(1);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (BPRVHPB.KEY.POOL_BOX_NO == null) BPRVHPB.KEY.POOL_BOX_NO = "";
        JIBS_tmp_int = BPRVHPB.KEY.POOL_BOX_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRVHPB.KEY.POOL_BOX_NO += " ";
        BPRVHPB.KEY.POOL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO.substring(0, 2 - 1) + JIBS_tmp_str[0].substring(11 - 1, 11 + 5 - 1) + BPRVHPB.KEY.POOL_BOX_NO.substring(2 + 5 - 1);
        BPRVHPB.POOL_BOX_NM = BPCSVHPB.INFO.PLBOX_NM;
        BPRVHPB.BR = BPCSVHPB.INFO.BR;
        BPRVHPB.POLL_BOX_IND = BPCSVHPB.INFO.PL_IND;
        if (BPCSVHPB.INFO.CUR_TLR.trim().length() > 0) {
            BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
            BPRVHPB.RELATE_FLG = 'Y';
        } else {
            BPRVHPB.CUR_TLR = " ";
            BPRVHPB.RELATE_FLG = 'N';
        }
        BPRVHPB.LST_TLR = " ";
        CEP.TRC(SCCGWA, BPCSVHPB.INFO.CUR_TLR);
        if (BPCSVHPB.INFO.CUR_TLR.trim().length() > 0) {
            BPRVHPB.BV_CHK_FLG = 'N';
            BPRVHPB.BL_CHK_FLG = 'N';
        } else {
            BPRVHPB.BV_CHK_FLG = 'Y';
            BPRVHPB.BL_CHK_FLG = 'Y';
        }
        BPRVHPB.STS = 'N';
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.BIND_TYPE = BPCSVHPB.INFO.BIND_TYP;
        BPCRVHPB.INFO.FUNC = 'A';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
        BPCRVHPB.INFO.FUNC = 'R';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        BPRVHPB.POOL_BOX_NM = BPCSVHPB.INFO.PLBOX_NM;
        if (BPCSVHPB.INFO.CUR_TLR.trim().length() > 0) {
            BPRVHPB.LST_TLR = BPRVHPB.CUR_TLR;
            BPRVHPB.CUR_TLR = BPCSVHPB.INFO.CUR_TLR;
            BPRVHPB.RELATE_FLG = 'Y';
        } else {
            BPRVHPB.LST_TLR = BPRVHPB.CUR_TLR;
            BPRVHPB.CUR_TLR = " ";
            BPRVHPB.RELATE_FLG = 'N';
        }
        BPRVHPB.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRVHPB.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRVHPB.BIND_TYPE = BPCSVHPB.INFO.BIND_TYP;
        BPCRVHPB.INFO.FUNC = 'U';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
        BPCRVHPB.INFO.FUNC = 'R';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        BPCRVHPB.INFO.FUNC = 'D';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B050_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.KEY.POOL_BOX_NO = BPCSVHPB.INFO.PLBOX_NO;
        BPCRVHPB.INFO.FUNC = 'Q';
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void B020_01_GET_POOL_SEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "PLBOX";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
    }
    public void B090_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOVHPB);
        BPCOVHPB.POOL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        BPCOVHPB.POOL_BOX_NM = BPRVHPB.POOL_BOX_NM;
        BPCOVHPB.BR = BPRVHPB.BR;
        BPCOVHPB.POLL_BOX_IND = BPRVHPB.POLL_BOX_IND;
        BPCOVHPB.CUR_TLR = BPRVHPB.CUR_TLR;
        BPCOVHPB.LST_TLR = BPRVHPB.LST_TLR;
        BPCOVHPB.RELATE_FLG = BPRVHPB.RELATE_FLG;
        BPCOVHPB.BV_CHK_FLG = BPRVHPB.BV_CHK_FLG;
        BPCOVHPB.BL_CHK_FLG = BPRVHPB.BL_CHK_FLG;
        BPCOVHPB.STS = BPRVHPB.STS;
        BPCOVHPB.UPD_DATE = BPRVHPB.UPD_DATE;
        BPCOVHPB.UPD_TLR = BPRVHPB.UPD_TLR;
        BPCOVHPB.CRT_DATE = BPRVHPB.CRT_DATE;
        BPCOVHPB.CRT_TLR = BPRVHPB.CRT_TLR;
        BPCOVHPB.BIND_TYP = BPRVHPB.BIND_TYPE;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP390";
        SCCFMT.DATA_PTR = BPCOVHPB;
        SCCFMT.DATA_LEN = 127;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B010_LIST_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 127;
        SCCMPAG.SCR_ROW_CNT = 99;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B010_LIST_TRANS_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_LINE_VAR);
        WS_LINE_VAR.WS_L_POOL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        WS_LINE_VAR.WS_L_POOL_BOX_NM = BPRVHPB.POOL_BOX_NM;
        WS_LINE_VAR.WS_L_BR = BPRVHPB.BR;
        WS_LINE_VAR.WS_L_POLL_BOX_IND = BPRVHPB.POLL_BOX_IND;
        WS_LINE_VAR.WS_L_CUR_TLR = BPRVHPB.CUR_TLR;
        WS_LINE_VAR.WS_L_LST_TLR = BPRVHPB.LST_TLR;
        WS_LINE_VAR.WS_L_RELATE_FLG = BPRVHPB.RELATE_FLG;
        WS_LINE_VAR.WS_L_BV_CHK_FLG = BPRVHPB.BV_CHK_FLG;
        WS_LINE_VAR.WS_L_BL_CHK_FLG = BPRVHPB.BL_CHK_FLG;
        WS_LINE_VAR.WS_L_STS = BPRVHPB.STS;
        WS_LINE_VAR.WS_L_UPD_DATE = BPRVHPB.UPD_DATE;
        WS_LINE_VAR.WS_L_UPD_TLR = BPRVHPB.UPD_TLR;
        WS_LINE_VAR.WS_L_CRT_DATE = BPRVHPB.CRT_DATE;
        WS_LINE_VAR.WS_L_CRT_TLR = BPRVHPB.CRT_TLR;
        WS_LINE_VAR.WS_L_BIND_TYPE = BPRVHPB.BIND_TYPE;
        CEP.TRC(SCCGWA, WS_LINE_VAR);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_LINE_VAR);
        SCCMPAG.DATA_LEN = 127;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTBDB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_TBVD, BPCRTBDB);
        if (BPCRTBDB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTBDB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSVHPB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTLSB() throws IOException,SQLException,Exception {
        BPCRTLSB.POINTER = BPRTLSC;
        BPCRTLSB.LEN = 736;
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLSB, BPCRTLSB);
        if (BPCRTLSB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTLSB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        BPCRVHPB.INFO.LEN = 152;
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        CEP.TRC(SCCGWA, BPCRVHPB.RC.RC_CODE);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
