package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMSCS {
    DBParm CITSCS_RD;
    DBParm CITADR_RD;
    DBParm CITCNT_RD;
    brParm CITSCS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI502";
    int K_MAX_ROW = 20;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_ERR_MSG = " ";
    int WS_I = 0;
    char WS_SCS_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRSCS CIRSCS = new CIRSCS();
    CIRSCS CIRSCSO = new CIRSCS();
    CIRSCS CIRSCSN = new CIRSCS();
    CIRADR CIRADR = new CIRADR();
    CIRCNT CIRCNT = new CIRCNT();
    CICOSCSL CICOSCSL = new CICOSCSL();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMSCS CICMSCS;
    public void MP(SCCGWA SCCGWA, CICMSCS CICMSCS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMSCS = CICMSCS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMSCS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRSCS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICMSCS.FUNC == 'F') {
            B020_FIRST_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'E') {
            B050_DELETE_ALL_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'L') {
            B060_BLEND_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'R') {
            B080_BRWADR_PROC();
            if (pgmRtn) return;
        } else if (CICMSCS.FUNC == 'C') {
            B080_BRWCNT_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = CICMSG_ERROR_MSG.CI_INF_INPUT_ERR;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_FIRST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_READ_CITSCS_FIRST();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CIRSCS.KEY.SUB_SEQ = CICMSCS.DATA.SUB_SEQ;
        T000_READ_CITSCS();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        IBS.init(SCCGWA, CIRSCSO);
        IBS.init(SCCGWA, CIRSCSN);
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_READ_CITSCS_DESC();
        if (pgmRtn) return;
        if (CIRSCS.KEY.SUB_SEQ < 99) {
            CIRSCS.KEY.SUB_SEQ = (short) (CIRSCS.KEY.SUB_SEQ + 1);
            CICMSCS.DATA.SUB_SEQ = CIRSCS.KEY.SUB_SEQ;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INF_INPUT_ERR);
        }
        R000_TRANS_DATA_ADD_TO_TBL();
        if (pgmRtn) return;
        T000_WRITE_CITSCS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRSCS, CIRSCSN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        IBS.init(SCCGWA, CIRSCSO);
        IBS.init(SCCGWA, CIRSCSN);
        if ((CICMSCS.DATA.STC_SEQ == ' ')) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STC_SEQ_NOT_NULL);
        }
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CIRSCS.KEY.SUB_SEQ = CICMSCS.DATA.SUB_SEQ;
        T000_READ_CITSCS_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRSCS, CIRSCSO);
        R000_TRANS_DATA_MD_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_CITSCS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRSCS, CIRSCSN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        IBS.init(SCCGWA, CIRSCSO);
        IBS.init(SCCGWA, CIRSCSN);
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CIRSCS.KEY.SUB_SEQ = CICMSCS.DATA.SUB_SEQ;
        CEP.TRC(SCCGWA, CICMSCS.DATA.STC_SEQ);
        CEP.TRC(SCCGWA, CICMSCS.DATA.CI_NO);
        CEP.TRC(SCCGWA, CICMSCS.DATA.SUB_SEQ);
        T000_READ_CITSCS_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRSCS, CIRSCSO);
        T000_DELETE_CITSCS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_ALL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        IBS.init(SCCGWA, CIRSCSO);
        IBS.init(SCCGWA, CIRSCSN);
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICMSCS.DATA.STC_SEQ);
        CEP.TRC(SCCGWA, CICMSCS.DATA.CI_NO);
        T000_DELETE_ALL_CITSCS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B060_BLEND_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        if ((CICMSCS.DATA.STC_SEQ == ' ')) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STC_SEQ_NOT_NULL);
        }
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CIRSCS.KEY.SUB_SEQ = CICMSCS.DATA.SUB_SEQ;
        T000_READ_CITSCS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else {
            B030_ADD_PROC();
            if (pgmRtn) return;
        }
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_STARTBR_CITSCS();
        if (pgmRtn) return;
        T000_READNEXT_CITSCS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITSCS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITSCS();
        if (pgmRtn) return;
    }
    public void B080_BRWADR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        IBS.init(SCCGWA, CIRADR);
        CIRADR.SRC_NO = CICMSCS.DATA.ADR_SEQ;
        CIRADR.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_READ_CITADR();
        if (pgmRtn) return;
        CIRSCS.ADR_SRC = CICMSCS.DATA.ADR_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_STARTBR_CITSCS_BYADR();
        if (pgmRtn) return;
        T000_READNEXT_CITSCS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITSCS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITSCS();
        if (pgmRtn) return;
    }
    public void B080_BRWCNT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRSCS);
        IBS.init(SCCGWA, CIRCNT);
        CIRCNT.SRC_NO = CICMSCS.DATA.CONT_SEQ;
        CIRCNT.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_READ_CITCNT();
        if (pgmRtn) return;
        CIRSCS.CNT_SRC = CICMSCS.DATA.CONT_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        T000_STARTBR_CITSCS_BYCNT();
        if (pgmRtn) return;
        T000_READNEXT_CITSCS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITSCS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITSCS();
        if (pgmRtn) return;
    }
    public void B080_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B080_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSCSL);
        CICOSCSL.SUB_SEQ = CIRSCS.KEY.SUB_SEQ;
        CICOSCSL.ADRS = CIRSCS.ADRS;
        CICOSCSL.SEND_TYP = CIRSCS.SEND_TYP;
        CICOSCSL.ADR_NM = CIRADR.ADR_NM;
        CICOSCSL.CNT_INFO = CIRCNT.CNT_INFO;
        CICOSCSL.PRT_NUM = CIRSCS.PRT_NUM;
        CICOSCSL.EFF_DT = CIRSCS.EFF_DT;
        CICOSCSL.EXP_DT = CIRSCS.EXP_DT;
        CEP.TRC(SCCGWA, CIRADR.ADR_NM);
        CEP.TRC(SCCGWA, CIRCNT.CNT_INFO);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOSCSL);
        SCCMPAG.DATA_LEN = 529;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_SAVE_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
    }
    public void R000_TRANS_DATA_ADD_TO_TBL() throws IOException,SQLException,Exception {
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CIRSCS.ADRS = CICMSCS.DATA.ADRS;
        CIRSCS.SEND_TYP = CICMSCS.DATA.SEND_TYP;
        CIRSCS.ADR_SRC = CICMSCS.DATA.ADR_SEQ;
        CIRSCS.CNT_SRC = CICMSCS.DATA.CONT_SEQ;
        CIRSCS.PRT_NUM = CICMSCS.DATA.PRT_NUM;
        CIRSCS.EFF_DT = CICMSCS.DATA.EFF_DT;
        CIRSCS.EXP_DT = CICMSCS.DATA.EXP_DT;
        CIRSCS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRSCS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRSCS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRSCS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRSCS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRSCS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_TRANS_DATA_MD_TO_TBL() throws IOException,SQLException,Exception {
        CIRSCS.KEY.STC_SEQ = CICMSCS.DATA.STC_SEQ;
        CIRSCS.KEY.CI_NO = CICMSCS.DATA.CI_NO;
        CIRSCS.KEY.SUB_SEQ = CICMSCS.DATA.SUB_SEQ;
        CIRSCS.ADRS = CICMSCS.DATA.ADRS;
        CIRSCS.SEND_TYP = CICMSCS.DATA.SEND_TYP;
        CIRSCS.ADR_SRC = CICMSCS.DATA.ADR_SEQ;
        CIRSCS.CNT_SRC = CICMSCS.DATA.CONT_SEQ;
        CIRSCS.PRT_NUM = CICMSCS.DATA.PRT_NUM;
        CIRSCS.EFF_DT = CICMSCS.DATA.EFF_DT;
        CIRSCS.EXP_DT = CICMSCS.DATA.EXP_DT;
        CIRSCS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRSCS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRSCS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICMSCS.DATA.STC_SEQ = CIRSCS.KEY.STC_SEQ;
        CICMSCS.DATA.CI_NO = CIRSCS.KEY.CI_NO;
        CICMSCS.DATA.SUB_SEQ = CIRSCS.KEY.SUB_SEQ;
        CICMSCS.DATA.ADRS = CIRSCS.ADRS;
        CICMSCS.DATA.SEND_TYP = CIRSCS.SEND_TYP;
        CICMSCS.DATA.ADR_SEQ = CIRSCS.ADR_SRC;
        CICMSCS.DATA.CONT_SEQ = CIRSCS.CNT_SRC;
        CICMSCS.DATA.PRT_NUM = CIRSCS.PRT_NUM;
        CICMSCS.DATA.EFF_DT = CIRSCS.EFF_DT;
        CICMSCS.DATA.EXP_DT = CIRSCS.EXP_DT;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMSCS.FUNC == 'A' 
            || CICMSCS.FUNC == 'M' 
            || CICMSCS.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICMSCS;
        SCCFMT.DATA_LEN = 380;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITSCS_FIRST() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        CITSCS_RD.eqWhere = "CI_NO,STC_SEQ";
        CITSCS_RD.fst = true;
        IBS.READ(SCCGWA, CIRSCS, CITSCS_RD);
    }
    public void T000_READ_CITADR() throws IOException,SQLException,Exception {
        CITADR_RD = new DBParm();
        CITADR_RD.TableName = "CITADR";
        CITADR_RD.where = "CI_NO = :CIRADR.KEY.CI_NO "
            + "AND SRC_NO = :CIRADR.SRC_NO";
        CITADR_RD.col = "CI_NO,ADR_NM";
        IBS.READ(SCCGWA, CIRADR, this, CITADR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ADR_NOTFND, CICMSCS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITCNT() throws IOException,SQLException,Exception {
        CITCNT_RD = new DBParm();
        CITCNT_RD.TableName = "CITCNT";
        CITCNT_RD.col = "CI_NO,CNT_INFO";
        IBS.READ(SCCGWA, CIRCNT, CITCNT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_CNT_NOTFND, CICMSCS.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITSCS() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        IBS.READ(SCCGWA, CIRSCS, CITSCS_RD);
    }
    public void T000_READ_CITSCS_DESC() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        CITSCS_RD.col = "SUB_SEQ";
        CITSCS_RD.where = "STC_SEQ = :CIRSCS.KEY.STC_SEQ "
            + "AND CI_NO = :CIRSCS.KEY.CI_NO";
        CITSCS_RD.fst = true;
        CITSCS_RD.order = "SUB_SEQ DESC";
        IBS.READ(SCCGWA, CIRSCS, this, CITSCS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CIRSCS.KEY.SUB_SEQ = 0;
        }
    }
    public void T000_WRITE_CITSCS() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        IBS.WRITE(SCCGWA, CIRSCS, CITSCS_RD);
    }
    public void T000_READ_CITSCS_UPD() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        CITSCS_RD.upd = true;
        IBS.READ(SCCGWA, CIRSCS, CITSCS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_SCS_INF_NOTFND);
        }
    }
    public void T000_REWRITE_CITSCS() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        IBS.REWRITE(SCCGWA, CIRSCS, CITSCS_RD);
    }
    public void T000_DELETE_CITSCS() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        IBS.DELETE(SCCGWA, CIRSCS, CITSCS_RD);
    }
    public void T000_DELETE_ALL_CITSCS() throws IOException,SQLException,Exception {
        CITSCS_RD = new DBParm();
        CITSCS_RD.TableName = "CITSCS";
        CITSCS_RD.where = "STC_SEQ = :CIRSCS.KEY.STC_SEQ "
            + "AND CI_NO = :CIRSCS.KEY.CI_NO";
        IBS.DELETE(SCCGWA, CIRSCS, this, CITSCS_RD);
    }
    public void T000_STARTBR_CITSCS() throws IOException,SQLException,Exception {
        CITSCS_BR.rp = new DBParm();
        CITSCS_BR.rp.TableName = "CITSCS";
        CITSCS_BR.rp.where = "STC_SEQ = :CIRSCS.KEY.STC_SEQ "
            + "AND CI_NO = :CIRSCS.KEY.CI_NO";
        IBS.STARTBR(SCCGWA, CIRSCS, this, CITSCS_BR);
    }
    public void T000_STARTBR_CITSCS_BYADR() throws IOException,SQLException,Exception {
        CITSCS_BR.rp = new DBParm();
        CITSCS_BR.rp.TableName = "CITSCS";
        CITSCS_BR.rp.where = "CI_NO = :CIRSCS.KEY.CI_NO "
            + "AND ADR_SRC = :CIRSCS.ADR_SRC";
        IBS.STARTBR(SCCGWA, CIRSCS, this, CITSCS_BR);
    }
    public void T000_STARTBR_CITSCS_BYCNT() throws IOException,SQLException,Exception {
        CITSCS_BR.rp = new DBParm();
        CITSCS_BR.rp.TableName = "CITSCS";
        CITSCS_BR.rp.where = "CI_NO = :CIRSCS.KEY.CI_NO "
            + "AND CNT_SRC = :CIRSCS.CNT_SRC";
        IBS.STARTBR(SCCGWA, CIRSCS, this, CITSCS_BR);
    }
    public void T000_READNEXT_CITSCS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRSCS, this, CITSCS_BR);
    }
    public void T000_ENDBR_CITSCS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITSCS_BR);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (CICMSCS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMSCS=");
            CEP.TRC(SCCGWA, CICMSCS);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
