package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQZSFRZ {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm EQTFRZ_RD;
    DBParm EQTFRZD_RD;
    DBParm EQTBVT_RD;
    brParm EQTFRZ_BR = new brParm();
    brParm EQTFRZD_BR = new brParm();
    boolean pgmRtn = false;
    int K_MAX_ROW = 12;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "EQ404";
    String K_CMP_CALL_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String K_HIS_FMT = "EQCSFRZ";
    String K_HIS_RMK1 = "EQ FREEZE";
    String K_HIS_RMK2 = "EQ CONTINUE TO FREEZE";
    String K_HIS_RMK3 = "EQ THAW";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_FRZ_NO = " ";
    char WS_BVT_FLG = ' ';
    char WS_FRZ_FLG = ' ';
    char WS_FRZD_FLG = ' ';
    char WS_OUTPUT_FLG = ' ';
    String WS_CI_NO = " ";
    String WS_PSBK_NO = " ";
    String WS_SPR_NM = " ";
    String WS_HIS_REMARK = " ";
    String WS_TX_MMO = " ";
    char WS_NORMAL_STS = 'N';
    int WS_HLD_SEQ = 0;
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRBVT EQRBVT = new EQRBVT();
    EQRFRZ EQRFRZ = new EQRFRZ();
    EQRFRZD EQRFRZD = new EQRFRZD();
    CICCUST CICCUST = new CICCUST();
    EQCRACT EQCRACT = new EQCRACT();
    EQCRFRZ EQCRFRZ = new EQCRFRZ();
    EQCOQ400_OPT_8400 EQCOQ400_OPT_8400 = new EQCOQ400_OPT_8400();
    EQCOQ404_OPT_8404 EQCOQ404_OPT_8404 = new EQCOQ404_OPT_8404();
    EQCOQ405_OPT_8405 EQCOQ405_OPT_8405 = new EQCOQ405_OPT_8405();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCSFRZ EQCSFRZ;
    public void MP(SCCGWA SCCGWA, EQCSFRZ EQCSFRZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCSFRZ = EQCSFRZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZSFRZ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (EQCSFRZ.DATA.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCSFRZ.FUNC);
        if (EQCSFRZ.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'F') {
            B030_FREEZE_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'O') {
            B040_CON_FRZ_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'T') {
            B050_THAW_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'R') {
            B060_READ_FIRST_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'B') {
            B060_BROWSE_ALL_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'A') {
            B060_BROWSE_EQAC_PROC();
            if (pgmRtn) return;
        } else if (EQCSFRZ.FUNC == 'C') {
            B070_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZ);
        IBS.init(SCCGWA, EQCRFRZ);
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZ.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        EQCRFRZ.FUNC = 'C';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        B020_01_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        B020_01_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_FREEZE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        if (EQRACT.AC_STS == 'C') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B030_01_GET_FRZSEQ();
        if (pgmRtn) return;
        B030_02_WRITE_EQTFRZ();
        if (pgmRtn) return;
        B030_03_WRITE_EQTFRZD();
        if (pgmRtn) return;
        EQRACT.FRZ_QTY = EQRACT.FRZ_QTY + EQCSFRZ.DATA.FRZ_QTY;
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        WS_HIS_REMARK = K_HIS_RMK1;
        WS_TX_MMO = "P129";
        R000_NFIANCE_HIS();
        if (pgmRtn) return;
    }
    public void B040_CON_FRZ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        if (EQRACT.AC_STS == 'C') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, EQRFRZ);
        IBS.init(SCCGWA, EQCRFRZ);
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZ.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        EQCRFRZ.FUNC = 'R';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        if (EQRFRZ.FRZ_STS == 'C') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_FRZ_THAW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQRFRZ.EXP_DT = EQCSFRZ.DATA.N_EXP_DT;
        EQRFRZ.CONT_FRZ_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.REMARK = EQCSFRZ.DATA.REMARK;
        EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZ.LAW_NM1 = EQCSFRZ.DATA.LAW_NM1;
        EQRFRZ.LAW_BNO1 = EQCSFRZ.DATA.LAW_BNO1;
        EQRFRZ.LAW_ENO1 = EQCSFRZ.DATA.LAW_ENO1;
        EQRFRZ.LAW_NM2 = EQCSFRZ.DATA.LAW_NM2;
        EQRFRZ.LAW_BNO2 = EQCSFRZ.DATA.LAW_BNO2;
        EQRFRZ.LAW_ENO2 = EQCSFRZ.DATA.LAW_ENO2;
        EQRFRZ.RELEASE_DT = 0;
        EQCRFRZ.FUNC = 'U';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        B040_01_WRITE_EQTFRZD();
        if (pgmRtn) return;
        WS_HIS_REMARK = K_HIS_RMK2;
        WS_TX_MMO = "P144";
        R000_NFIANCE_HIS();
        if (pgmRtn) return;
    }
    public void B050_THAW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        if (EQRACT.AC_STS == 'C') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_AC_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, EQRFRZ);
        IBS.init(SCCGWA, EQCRFRZ);
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZ.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        EQCRFRZ.FUNC = 'R';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        if (EQRFRZ.FRZ_STS == 'C') {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_FRZ_THAW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQRFRZ.FRZ_QTY = EQRFRZ.FRZ_QTY - EQCSFRZ.DATA.REL_QTY;
        EQRFRZ.REL_QTY = EQRFRZ.REL_QTY + EQCSFRZ.DATA.REL_QTY;
        if (EQCSFRZ.DATA.FRZ_QTY == EQCSFRZ.DATA.REL_QTY) {
            EQRFRZ.FRZ_STS = 'C';
            EQRFRZ.ACT_REL_DT = SCCGWA.COMM_AREA.AC_DATE;
            EQRFRZ.RELEASE_DT = SCCGWA.COMM_AREA.AC_DATE;
        } else {
            EQRFRZ.PART_REL_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        EQRFRZ.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRFRZ.REL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZ.REL_NO = EQCSFRZ.DATA.R_SPR_NO;
        EQRFRZ.REL_NM = EQCSFRZ.DATA.R_SPR_NM;
        EQRFRZ.REMARK = EQCSFRZ.DATA.REMARK;
        EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZ.LAW_NM1 = EQCSFRZ.DATA.LAW_NM1;
        EQRFRZ.LAW_BNO1 = EQCSFRZ.DATA.LAW_BNO1;
        EQRFRZ.LAW_ENO1 = EQCSFRZ.DATA.LAW_ENO1;
        EQRFRZ.LAW_NM2 = EQCSFRZ.DATA.LAW_NM2;
        EQRFRZ.LAW_BNO2 = EQCSFRZ.DATA.LAW_BNO2;
        EQRFRZ.LAW_ENO2 = EQCSFRZ.DATA.LAW_ENO2;
        EQCRFRZ.FUNC = 'U';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        B050_01_WRITE_EQTFRZD();
        if (pgmRtn) return;
        EQRACT.FRZ_QTY = EQRACT.FRZ_QTY - EQCSFRZ.DATA.REL_QTY;
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        WS_HIS_REMARK = K_HIS_RMK3;
        WS_TX_MMO = "P130";
        R000_NFIANCE_HIS();
        if (pgmRtn) return;
    }
    public void B060_READ_FIRST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        if (EQCSFRZ.SUB_FUNC == 'C') {
            EQRACT.CI_NO = EQCSFRZ.DATA.CI_NO;
            EQCRACT.FUNC = 'C';
        } else if (EQCSFRZ.SUB_FUNC == 'T') {
            EQRACT.EQ_ACT = EQCSFRZ.DATA.EQ_ACT;
            EQCRACT.FUNC = 'E';
        } else if (EQCSFRZ.SUB_FUNC == 'E') {
            EQRACT.EQ_CINO = EQCSFRZ.DATA.EQ_CINO;
            EQCRACT.FUNC = 'I';
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_SUB_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        EQCSFRZ.DATA.EQ_AC = EQRACT.KEY.EQ_AC;
        B060_BROWSE_EQAC_PROC();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_ALL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZ);
        WS_FRZ_FLG = ' ';
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        if (EQCSFRZ.SUB_FUNC == 'P') {
            EQRFRZ.SPR_NM = EQCSFRZ.DATA.SPR_NM;
            EQRFRZ.SPR_NM = EQRFRZ.SPR_NM.replace(" ", "%");
            T000_STARTBR_EQTFRZ();
            if (pgmRtn) return;
        } else if (EQCSFRZ.SUB_FUNC == 'S') {
            EQRFRZ.FRZ_STS = EQCSFRZ.DATA.FRZ_STS;
            T001_STARTBR_EQTFRZ();
            if (pgmRtn) return;
        } else if (EQCSFRZ.SUB_FUNC == 'B') {
            EQRFRZ.SPR_NM = EQCSFRZ.DATA.SPR_NM;
            EQRFRZ.SPR_NM = EQRFRZ.SPR_NM.replace(" ", "%");
            EQRFRZ.FRZ_STS = EQCSFRZ.DATA.FRZ_STS;
            T002_STARTBR_EQTFRZ();
            if (pgmRtn) return;
        } else if (EQCSFRZ.SUB_FUNC == 'A') {
            T003_STARTBR_EQTFRZ();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_SUB_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READNEXT_EQTFRZ();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            T000_READ_ACT_BVT_TABLE();
            if (pgmRtn) return;
            B060_01_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTFRZ();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTFRZ();
        if (pgmRtn) return;
    }
    public void B060_BROWSE_EQAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRFRZ);
        WS_FRZ_FLG = ' ';
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZ.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        T004_STARTBR_EQTFRZ();
        if (pgmRtn) return;
        T000_READNEXT_EQTFRZ();
        if (pgmRtn) return;
        if (WS_FRZ_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_FRZ_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_OUTPUT_FLG = 'N';
            if (EQCSFRZ.DATA.SPR_NM.trim().length() == 0 
                && EQCSFRZ.DATA.FRZ_STS == ' ') {
                WS_OUTPUT_FLG = 'Y';
            }
            if (EQCSFRZ.DATA.SPR_NM.trim().length() == 0 
                && EQCSFRZ.DATA.FRZ_STS == EQRFRZ.FRZ_STS) {
                WS_OUTPUT_FLG = 'Y';
            }
            if (EQCSFRZ.DATA.SPR_NM.equalsIgnoreCase(EQRFRZ.SPR_NM) 
                && EQCSFRZ.DATA.FRZ_STS == ' ') {
                WS_OUTPUT_FLG = 'Y';
            }
            if (EQCSFRZ.DATA.SPR_NM.equalsIgnoreCase(EQRFRZ.SPR_NM) 
                && EQCSFRZ.DATA.FRZ_STS == EQRFRZ.FRZ_STS) {
                WS_OUTPUT_FLG = 'Y';
            }
            if (WS_OUTPUT_FLG == 'Y') {
                T000_READ_BV_FIRST();
                if (pgmRtn) return;
                B060_01_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_EQTFRZ();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTFRZ();
        if (pgmRtn) return;
    }
    public void B070_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZD);
        WS_FRZD_FLG = ' ';
        EQRFRZD.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZD.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQRFRZD.KEY.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        T005_STARTBR_EQTFRZD();
        if (pgmRtn) return;
        T000_READNEXT_EQTFRZD();
        if (pgmRtn) return;
        if (WS_FRZD_FLG == 'Y') {
            B060_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_FRZD_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B060_03_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTFRZD();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTFRZD();
        if (pgmRtn) return;
    }
    public void B020_01_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = EQCOQ404_OPT_8404;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B020_01_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ404_OPT_8404);
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        WS_HLD_SEQ = 0;
        EQRACT.KEY.EQ_BKID = EQRFRZ.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRFRZ.KEY.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ404_OPT_8404.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ404_OPT_8404.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ404_OPT_8404.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T001_READ_EQTFRZ();
        if (pgmRtn) return;
        WS_HLD_SEQ += 1;
        EQCOQ404_OPT_8404.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCOQ404_OPT_8404.FRZ_NO = EQRFRZ.FRZ_NO;
        EQCOQ404_OPT_8404.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCOQ404_OPT_8404.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ404_OPT_8404.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ404_OPT_8404.CI_NO = EQRACT.CI_NO;
        EQCOQ404_OPT_8404.PSBK_NO = WS_PSBK_NO;
        EQCOQ404_OPT_8404.FRZ_QTY = EQRFRZ.FRZ_QTY;
        EQCOQ404_OPT_8404.REL_QTY = EQRFRZ.REL_QTY;
        EQCOQ404_OPT_8404.EFF_DT = EQRFRZ.EFF_DT;
        EQCOQ404_OPT_8404.EXP_DT = EQRFRZ.EXP_DT;
        EQCOQ404_OPT_8404.A_REL_DT = EQRFRZ.PART_REL_DT;
        EQCOQ404_OPT_8404.C_FRZ_DT = EQRFRZ.CONT_FRZ_DT;
        EQCOQ404_OPT_8404.SPR_NO = EQRFRZ.SPR_NO;
        EQCOQ404_OPT_8404.SPR_NM = EQRFRZ.SPR_NM;
        EQCOQ404_OPT_8404.HLD_SEQ = WS_HLD_SEQ;
        EQCOQ404_OPT_8404.FRZ_STS = EQRFRZ.FRZ_STS;
        EQCOQ404_OPT_8404.REMARK = EQRFRZ.REMARK;
        EQCOQ404_OPT_8404.LAW_NM1 = EQRFRZ.LAW_NM1;
        EQCOQ404_OPT_8404.LAW_BNO1 = EQRFRZ.LAW_BNO1;
        EQCOQ404_OPT_8404.LAW_ENO1 = EQRFRZ.LAW_ENO1;
        EQCOQ404_OPT_8404.LAW_NM2 = EQRFRZ.LAW_NM2;
        EQCOQ404_OPT_8404.LAW_BNO2 = EQRFRZ.LAW_BNO2;
        EQCOQ404_OPT_8404.LAW_ENO2 = EQRFRZ.LAW_ENO2;
        EQCOQ404_OPT_8404.REQ_BR = EQRFRZ.OWNER_BK;
        EQCOQ404_OPT_8404.REL_DT = EQRFRZ.RELEASE_DT;
        EQCOQ404_OPT_8404.REL_NM = EQRFRZ.REL_NM;
        EQCOQ404_OPT_8404.REL_NO = EQRFRZ.REL_NO;
        EQCOQ404_OPT_8404.REL_RSN = EQRFRZ.REL_RSN;
        EQCOQ404_OPT_8404.OWNER_BR = EQRFRZ.OWNER_BK;
        EQCOQ404_OPT_8404.CRT_DATE = EQRFRZ.CRT_DATE;
        EQCOQ404_OPT_8404.CRT_TLR = EQRFRZ.CRT_TLR;
        EQCOQ404_OPT_8404.LSUP_DT = EQRFRZ.UPDTBL_DATE;
        EQCOQ404_OPT_8404.LSUP_TLR = EQRFRZ.UPDTBL_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = EQCOQ404_OPT_8404;
        SCCFMT.DATA_LEN = 0;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B030_01_GET_FRZSEQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "EQ";
        BPCSGSEQ.CODE = "FRZSEQ";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_FRZ_NO == null) WS_FRZ_NO = "";
        JIBS_tmp_int = WS_FRZ_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) WS_FRZ_NO += " ";
        WS_FRZ_NO = JIBS_tmp_str[0].substring(0, 4) + WS_FRZ_NO.substring(4);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (WS_FRZ_NO == null) WS_FRZ_NO = "";
        JIBS_tmp_int = WS_FRZ_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) WS_FRZ_NO += " ";
        WS_FRZ_NO = WS_FRZ_NO.substring(0, 5 - 1) + JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1) + WS_FRZ_NO.substring(5 + 9 - 1);
        CEP.TRC(SCCGWA, WS_FRZ_NO);
    }
    public void B030_02_WRITE_EQTFRZ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZ);
        IBS.init(SCCGWA, EQCRFRZ);
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZ.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQRFRZ.FRZ_NO = WS_FRZ_NO;
        EQRFRZ.FRZ_TYPE = '1';
        EQRFRZ.FRZ_QTY = EQCSFRZ.DATA.FRZ_QTY;
        EQRFRZ.REL_QTY = 0;
        EQRFRZ.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.EXP_DT = EQCSFRZ.DATA.RELSE_DT;
        EQRFRZ.ACT_REL_DT = 0;
        EQRFRZ.FRZ_STS = 'N';
        EQRFRZ.FRZ_STSWD = " ";
        EQRFRZ.CONT_FRZ_DT = 0;
        EQRFRZ.SPR_NO = EQCSFRZ.DATA.SPR_NO;
        EQRFRZ.SPR_NM = EQCSFRZ.DATA.SPR_NM;
        EQRFRZ.FRZ_RSN = " ";
        EQRFRZ.LAW_NM1 = EQCSFRZ.DATA.LAW_NM1;
        EQRFRZ.LAW_BNO1 = EQCSFRZ.DATA.LAW_BNO1;
        EQRFRZ.LAW_ENO1 = EQCSFRZ.DATA.LAW_ENO1;
        EQRFRZ.LAW_NM2 = EQCSFRZ.DATA.LAW_NM2;
        EQRFRZ.LAW_BNO2 = EQCSFRZ.DATA.LAW_BNO2;
        EQRFRZ.LAW_ENO2 = EQCSFRZ.DATA.LAW_ENO2;
        EQRFRZ.CHNL_NO = " ";
        EQRFRZ.REL_BR = 0;
        EQRFRZ.REL_TLR = " ";
        EQRFRZ.PART_REL_DT = 0;
        EQRFRZ.RELEASE_DT = 0;
        EQRFRZ.REL_NO = " ";
        EQRFRZ.REL_NM = " ";
        EQRFRZ.REL_RSN = " ";
        EQRFRZ.REMARK = EQCSFRZ.DATA.REMARK;
        EQRFRZ.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZ.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRFRZ.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZ.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZ.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRFRZ.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRFRZ.TS = "0" + EQRFRZ.TS;
        EQCRFRZ.FUNC = 'A';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
    }
    public void B030_03_WRITE_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZD);
        EQRFRZD.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZD.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQRFRZD.KEY.FRZ_NO = WS_FRZ_NO;
        EQRFRZD.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRFRZD.PREV_FRZ_QTY = EQRACT.FRZ_QTY;
        EQRFRZD.TXN_QTY = EQCSFRZ.DATA.FRZ_QTY;
        EQRFRZD.PROC_FLG = '1';
        EQRFRZD.UPT_NM = EQCSFRZ.DATA.SPR_NM;
        EQRFRZD.UPT_NO = EQCSFRZ.DATA.SPR_NO;
        EQRFRZD.UPT_RSN = "FRZZSE";
        EQRFRZD.DEDUCT_FLG = ' ';
        EQRFRZD.RVS_FLG = ' ';
        EQRFRZD.LAW_NM1 = EQCSFRZ.DATA.LAW_NM1;
        EQRFRZD.LAW_BNO1 = EQCSFRZ.DATA.LAW_BNO1;
        EQRFRZD.LAW_ENO1 = EQCSFRZ.DATA.LAW_ENO1;
        EQRFRZD.LAW_NM2 = EQCSFRZ.DATA.LAW_NM2;
        EQRFRZD.LAW_BNO2 = EQCSFRZ.DATA.LAW_BNO2;
        EQRFRZD.LAW_ENO2 = EQCSFRZ.DATA.LAW_ENO2;
        EQRFRZD.CHNL_NO = " ";
        EQRFRZD.REMARK = EQCSFRZ.DATA.REMARK;
        EQRFRZD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRFRZD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRFRZD.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRFRZD.TS = "0" + EQRFRZD.TS;
        T000_WRITE_EQTFRZD();
        if (pgmRtn) return;
    }
    public void B040_01_WRITE_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZD);
        EQRFRZD.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZD.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQRFRZD.KEY.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        EQRFRZD.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRFRZD.PREV_FRZ_QTY = EQCSFRZ.DATA.FRZ_QTY;
        EQRFRZD.TXN_QTY = EQCSFRZ.DATA.FRZ_QTY;
        EQRFRZD.PROC_FLG = '2';
        EQRFRZD.UPT_NM = EQCSFRZ.DATA.SPR_NM;
        EQRFRZD.UPT_NO = EQCSFRZ.DATA.C_SPR_NO;
        EQRFRZD.UPT_RSN = EQCSFRZ.DATA.CONT_RSN;
        EQRFRZD.DEDUCT_FLG = ' ';
        EQRFRZD.RVS_FLG = ' ';
        EQRFRZD.LAW_NM1 = EQCSFRZ.DATA.LAW_NM1;
        EQRFRZD.LAW_BNO1 = EQCSFRZ.DATA.LAW_BNO1;
        EQRFRZD.LAW_ENO1 = EQCSFRZ.DATA.LAW_ENO1;
        EQRFRZD.LAW_NM2 = EQCSFRZ.DATA.LAW_NM2;
        EQRFRZD.LAW_BNO2 = EQCSFRZ.DATA.LAW_BNO2;
        EQRFRZD.LAW_ENO2 = EQCSFRZ.DATA.LAW_ENO2;
        EQRFRZD.CHNL_NO = " ";
        EQRFRZD.REMARK = EQCSFRZ.DATA.REMARK;
        EQRFRZD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRFRZD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRFRZD.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRFRZD.TS = "0" + EQRFRZD.TS;
        T000_WRITE_EQTFRZD();
        if (pgmRtn) return;
    }
    public void B050_01_WRITE_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRFRZD);
        EQRFRZD.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZD.KEY.EQ_AC = EQCSFRZ.DATA.EQ_AC;
        EQRFRZD.KEY.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        EQRFRZD.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRFRZD.PREV_FRZ_QTY = EQCSFRZ.DATA.FRZ_QTY;
        EQRFRZD.TXN_QTY = EQCSFRZ.DATA.REL_QTY;
        EQRFRZD.PROC_FLG = '3';
        EQRFRZD.UPT_NM = EQCSFRZ.DATA.R_SPR_NM;
        EQRFRZD.UPT_NO = EQCSFRZ.DATA.R_SPR_NO;
        EQRFRZD.UPT_RSN = " ";
        EQRFRZD.DEDUCT_FLG = ' ';
        EQRFRZD.RVS_FLG = ' ';
        EQRFRZD.LAW_NM1 = EQCSFRZ.DATA.LAW_NM1;
        EQRFRZD.LAW_BNO1 = EQCSFRZ.DATA.LAW_BNO1;
        EQRFRZD.LAW_ENO1 = EQCSFRZ.DATA.LAW_ENO1;
        EQRFRZD.LAW_NM2 = EQCSFRZ.DATA.LAW_NM2;
        EQRFRZD.LAW_BNO2 = EQCSFRZ.DATA.LAW_BNO2;
        EQRFRZD.LAW_ENO2 = EQCSFRZ.DATA.LAW_ENO2;
        EQRFRZD.CHNL_NO = " ";
        EQRFRZD.REMARK = EQCSFRZ.DATA.REMARK;
        EQRFRZD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRFRZD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRFRZD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRFRZD.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRFRZD.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRFRZD.TS = "0" + EQRFRZD.TS;
        T000_WRITE_EQTFRZD();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_01_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ400_OPT_8400);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ400_OPT_8400.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ400_OPT_8400.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ400_OPT_8400.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQCOQ400_OPT_8400.EQ_BKID = EQRFRZ.KEY.EQ_BKID;
        EQCOQ400_OPT_8400.FRZ_NO = EQRFRZ.FRZ_NO;
        EQCOQ400_OPT_8400.EQ_AC = EQRFRZ.KEY.EQ_AC;
        EQCOQ400_OPT_8400.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ400_OPT_8400.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ400_OPT_8400.PSBK_NO = WS_PSBK_NO;
        EQCOQ400_OPT_8400.CI_NO = EQRACT.CI_NO;
        EQCOQ400_OPT_8400.FRZ_QTY = EQRFRZ.FRZ_QTY;
        EQCOQ400_OPT_8400.REL_QTY = EQRFRZ.REL_QTY;
        EQCOQ400_OPT_8400.EFF_DT = EQRFRZ.EFF_DT;
        EQCOQ400_OPT_8400.EXP_DT = EQRFRZ.EXP_DT;
        EQCOQ400_OPT_8400.A_REL_DT = EQRFRZ.PART_REL_DT;
        EQCOQ400_OPT_8400.SPR_NO = EQRFRZ.SPR_NO;
        EQCOQ400_OPT_8400.SPR_NM = EQRFRZ.SPR_NM;
        EQCOQ400_OPT_8400.FRZ_STS = EQRFRZ.FRZ_STS;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCOQ400_OPT_8400);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B060_03_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCOQ405_OPT_8405);
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQRFRZD.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRFRZD.KEY.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCOQ405_OPT_8405.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCOQ405_OPT_8405.ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
            EQCOQ405_OPT_8405.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, EQRFRZ);
        IBS.init(SCCGWA, EQCRFRZ);
        EQRFRZ.KEY.EQ_BKID = EQCSFRZ.DATA.EQ_BKID;
        EQRFRZ.FRZ_NO = EQCSFRZ.DATA.FRZ_NO;
        EQCRFRZ.FUNC = 'C';
        S000_CALL_EQZRFRZ();
        if (pgmRtn) return;
        EQCOQ405_OPT_8405.EQ_BKID = EQRFRZD.KEY.EQ_BKID;
        EQCOQ405_OPT_8405.FRZ_NO = EQRFRZD.KEY.FRZ_NO;
        EQCOQ405_OPT_8405.PROC_FLG = EQRFRZD.PROC_FLG;
        EQCOQ405_OPT_8405.EQ_AC = EQRFRZD.KEY.EQ_AC;
        EQCOQ405_OPT_8405.EQ_ACT = EQRACT.EQ_ACT;
        EQCOQ405_OPT_8405.EQ_CINO = EQRACT.EQ_CINO;
        EQCOQ405_OPT_8405.CI_NO = EQRACT.CI_NO;
        EQCOQ405_OPT_8405.PSBK_NO = WS_PSBK_NO;
        EQCOQ405_OPT_8405.TXN_DT = EQRFRZD.KEY.TXN_DATE;
        EQCOQ405_OPT_8405.BAT_NO = "" + EQRFRZD.JRN_NO;
        JIBS_tmp_int = EQCOQ405_OPT_8405.BAT_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) EQCOQ405_OPT_8405.BAT_NO = "0" + EQCOQ405_OPT_8405.BAT_NO;
        EQCOQ405_OPT_8405.PFRZ_QTY = EQRFRZD.PREV_FRZ_QTY;
        EQCOQ405_OPT_8405.TXN_QTY = EQRFRZD.TXN_QTY;
        EQCOQ405_OPT_8405.SPR_NM = EQRFRZ.SPR_NM;
        EQCOQ405_OPT_8405.UPT_NO = EQRFRZD.UPT_NO;
        EQCOQ405_OPT_8405.UPT_RSN = EQRFRZD.UPT_RSN;
        EQCOQ405_OPT_8405.DD_FLG = EQRFRZD.DEDUCT_FLG;
        EQCOQ405_OPT_8405.RVS_FLG = EQRFRZD.RVS_FLG;
        EQCOQ405_OPT_8405.LAW_NM1 = EQRFRZD.LAW_NM1;
        EQCOQ405_OPT_8405.LAW_BNO1 = EQRFRZD.LAW_BNO1;
        EQCOQ405_OPT_8405.LAW_ENO1 = EQRFRZD.LAW_ENO1;
        EQCOQ405_OPT_8405.LAW_NM2 = EQRFRZD.LAW_NM2;
        EQCOQ405_OPT_8405.LAW_BNO2 = EQRFRZD.LAW_BNO2;
        EQCOQ405_OPT_8405.LAW_ENO2 = EQRFRZD.LAW_ENO2;
        EQCOQ405_OPT_8405.REMARK = EQRFRZD.REMARK;
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405);
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405.SPR_NM);
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405.UPT_NO);
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405.UPT_RSN);
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405.DD_FLG);
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405.RVS_FLG);
        CEP.TRC(SCCGWA, EQCOQ405_OPT_8405.LAW_NM1);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCOQ405_OPT_8405);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_NFIANCE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.AC = EQCSFRZ.DATA.EQ_AC;
        BPCPNHIS.INFO.FMT_ID = K_HIS_FMT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = EQRACT.CI_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.TX_TYP_CD = WS_TX_MMO;
        BPCPNHIS.INFO.TX_RMK = WS_HIS_REMARK;
        BPCPNHIS.INFO.CCY = "156";
        BPCPNHIS.INFO.CCY_TYPE = '1';
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_EQZRACT() throws IOException,SQLException,Exception {
        EQCRACT.REC_PTR = EQRACT;
        EQCRACT.REC_LEN = 724;
        IBS.CALLCPN(SCCGWA, "EQ-RSC-EQTACT", EQCRACT);
        if (EQCRACT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, EQCRACT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_EQZRFRZ() throws IOException,SQLException,Exception {
        EQCRFRZ.REC_PTR = EQRFRZ;
        EQCRFRZ.REC_LEN = 1990;
        IBS.CALLCPN(SCCGWA, "EQ-RSC-EQTFRZ", EQCRFRZ);
        if (EQCRFRZ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, EQCRFRZ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_ACT_BVT_TABLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQCRACT);
        EQRACT.KEY.EQ_BKID = EQRFRZ.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRFRZ.KEY.EQ_AC;
        EQCRACT.FUNC = 'Q';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        T000_READ_BV_FIRST();
        if (pgmRtn) return;
    }
    public void T001_READ_EQTFRZ() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.set = "WS-HLD-SEQ=COUNT(*)";
        EQTFRZ_RD.where = "EQ_AC = :EQRACT.KEY.EQ_AC "
            + "AND FRZ_NO < :EQRFRZ.FRZ_NO "
            + "AND FRZ_STS = 'N'";
        IBS.GROUP(SCCGWA, EQRFRZ, this, EQTFRZ_RD);
    }
    public void T000_READ_EQTFRZD() throws IOException,SQLException,Exception {
        EQTFRZD_RD = new DBParm();
        EQTFRZD_RD.TableName = "EQTFRZD";
        EQTFRZD_RD.where = "EQ_BKID = :EQRFRZD.KEY.EQ_BKID "
            + "AND FRZ_NO = :EQRFRZD.KEY.FRZ_NO";
        EQTFRZD_RD.fst = true;
        IBS.READ(SCCGWA, EQRFRZD, this, EQTFRZD_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZD_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTFRZ_BY_EQAC() throws IOException,SQLException,Exception {
        EQTFRZ_RD = new DBParm();
        EQTFRZ_RD.TableName = "EQTFRZ";
        EQTFRZ_RD.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRFRZ.KEY.EQ_AC";
        IBS.READ(SCCGWA, EQRFRZ, this, EQTFRZ_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_BV_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        EQRBVT.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        T000_READ_EQTBVT();
        if (pgmRtn) return;
        if (WS_BVT_FLG == 'Y') {
            WS_PSBK_NO = EQRBVT.KEY.PSBK_NO;
        } else {
            WS_PSBK_NO = " ";
        }
    }
    public void T000_READ_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_STS = :WS_NORMAL_STS";
        EQTBVT_RD.fst = true;
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T000_WRITE_EQTFRZD() throws IOException,SQLException,Exception {
        EQTFRZD_RD = new DBParm();
        EQTFRZD_RD.TableName = "EQTFRZD";
        IBS.WRITE(SCCGWA, EQRFRZD, EQTFRZD_RD);
    }
    public void T000_STARTBR_EQTFRZ() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND SPR_NM LIKE :EQRFRZ.SPR_NM";
        EQTFRZ_BR.rp.order = "FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T001_STARTBR_EQTFRZ() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND FRZ_STS = :EQRFRZ.FRZ_STS";
        EQTFRZ_BR.rp.order = "FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T002_STARTBR_EQTFRZ() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND SPR_NM LIKE :EQRFRZ.SPR_NM "
            + "AND FRZ_STS = :EQRFRZ.FRZ_STS";
        EQTFRZ_BR.rp.order = "FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T003_STARTBR_EQTFRZ() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID";
        EQTFRZ_BR.rp.order = "FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T004_STARTBR_EQTFRZ() throws IOException,SQLException,Exception {
        EQTFRZ_BR.rp = new DBParm();
        EQTFRZ_BR.rp.TableName = "EQTFRZ";
        EQTFRZ_BR.rp.where = "EQ_BKID = :EQRFRZ.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRFRZ.KEY.EQ_AC";
        EQTFRZ_BR.rp.order = "FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
    }
    public void T005_STARTBR_EQTFRZD() throws IOException,SQLException,Exception {
        EQTFRZD_BR.rp = new DBParm();
        EQTFRZD_BR.rp.TableName = "EQTFRZD";
        EQTFRZD_BR.rp.where = "EQ_BKID = :EQRFRZD.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRFRZD.KEY.EQ_AC "
            + "AND FRZ_NO = :EQRFRZD.KEY.FRZ_NO";
        EQTFRZD_BR.rp.order = "TXN_DATE,FRZ_NO";
        IBS.STARTBR(SCCGWA, EQRFRZD, this, EQTFRZD_BR);
    }
    public void T000_READNEXT_EQTFRZ() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRFRZ, this, EQTFRZ_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZ_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZ_FLG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRFRZD, this, EQTFRZD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_FRZD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_FRZD_FLG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_EQTFRZ() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTFRZ_BR);
    }
    public void T000_ENDBR_EQTFRZD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTFRZD_BR);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
