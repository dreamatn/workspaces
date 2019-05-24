package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZMALT {
    int JIBS_tmp_int;
    DBParm CITALT_RD;
    DBParm CITBAS_RD;
    brParm CITALT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_RMK = "CI ALT INFO        ";
    String K_HIS_CPY = "CIRALT";
    String K_OUTPUT_FMT_X = "CIX01";
    String K_OUTPUT_FMT_9 = "CI033";
    String K_SEQ_TYPE = "SEQ";
    String K_SEQ_CD = "CIALTNO";
    int K_MAX_ROW = 25;
    int K_MAX_COL = 99;
    int K_COL_CNT = 3;
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    int WS_I = 0;
    int WS_O = 0;
    int WS_PAGE_ROW = 0;
    short WS_RECORD_NUM = 0;
    int WS_CX = 0;
    int WS_SEQ = 0;
    char WS_ALT_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    CIRALT CIRALT = new CIRALT();
    CIRALT CIRALTO = new CIRALT();
    CIRALT CIRALTN = new CIRALT();
    CIRBAS CIRBAS = new CIRBAS();
    CICOALTL CICOALTL = new CICOALTL();
    CICOALTO CICOALTO = new CICOALTO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    SCCBINF SCCBINF = new SCCBINF();
    int WS_CNT = 0;
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICMALT CICMALT;
    public void MP(SCCGWA SCCGWA, CICMALT CICMALT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICMALT = CICMALT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZMALT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CIRALT);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICMALT.FUNC == 'I') {
            B020_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICMALT.FUNC == 'A') {
            B030_ADD_PROC();
            if (pgmRtn) return;
        } else if (CICMALT.FUNC == 'M') {
            B040_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICMALT.FUNC == 'D') {
            B050_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICMALT.FUNC == 'B') {
            B080_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, 10);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (CICMALT.DATA.ENTY_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRBAS);
            CIRBAS.KEY.CI_NO = CICMALT.DATA.ENTY_NO;
            T000_READ_CITBAS_UPDATE();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CIRALT.KEY.ALT_NO = CICMALT.DATA.ALT_NO;
        T000_READ_CITALT();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B030_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        IBS.init(SCCGWA, CIRALTO);
        IBS.init(SCCGWA, CIRALTN);
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_SEQ_CD;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CICMALT.DATA.ALT_NO = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = CICMALT.DATA.ALT_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) CICMALT.DATA.ALT_NO = "0" + CICMALT.DATA.ALT_NO;
        CEP.TRC(SCCGWA, CICMALT.DATA.ALT_NO);
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_READ_CITALT_EXIST();
        if (pgmRtn) return;
        T000_WRITE_CITALT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALT, CIRALTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B040_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        IBS.init(SCCGWA, CIRALTO);
        IBS.init(SCCGWA, CIRALTN);
        CIRALT.KEY.ALT_NO = CICMALT.DATA.ALT_NO;
        T000_READ_CITALT_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALT, CIRALTO);
        R000_TRANS_DATA_TO_TBL();
        if (pgmRtn) return;
        T000_REWRITE_CITALT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALT, CIRALTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        IBS.init(SCCGWA, CIRALTO);
        IBS.init(SCCGWA, CIRALTN);
        CIRALT.KEY.ALT_NO = CICMALT.DATA.ALT_NO;
        T000_READ_CITALT_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRALT, CIRALTO);
        if (CIRALT.ALT_TYP.equalsIgnoreCase("0020")) {
            if (CIRALT.ENTY_NO.trim().length() > 0) {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRALT.ENTY_NO;
                T000_READ_CITBAS_UPDATE();
                if (pgmRtn) return;
                if (CIRBAS.STSW == null) CIRBAS.STSW = "";
                JIBS_tmp_int = CIRBAS.STSW.length();
                for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
                CIRBAS.STSW = CIRBAS.STSW.substring(0, 26 - 1) + "01" + CIRBAS.STSW.substring(26 + 2 - 1);
                CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_REWRITE_CITBAS();
                if (pgmRtn) return;
            }
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_TRD_NEED_AUTH);
        }
        T000_DELETE_CITALT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
        R000_DATA_TRANS_TO_FMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B080_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRALT);
        CIRALT.ENTY_TYP = CICMALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICMALT.DATA.ENTY_NO;
        CIRALT.ALT_TYP = CICMALT.DATA.ALT_TYP;
        CIRALT.MSG_CHNL = CICMALT.DATA.MSG_CHNL;
        CIRALT.EFF_DT = CICMALT.DATA.EFF_DT;
        CIRALT.EXP_DT = CICMALT.DATA.EXP_DT;
        CEP.TRC(SCCGWA, CIRALT.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRALT.ENTY_NO);
        CEP.TRC(SCCGWA, CIRALT.ALT_TYP);
        CEP.TRC(SCCGWA, CICMALT.OPT);
        if (CICMALT.OPT == 'T') {
            T000_STARTBR_CITALT_TYP();
            if (pgmRtn) return;
        } else if (CICMALT.OPT == 'L') {
            T000_STARTBR_CITALT_CHNL();
            if (pgmRtn) return;
        } else if (CICMALT.OPT == 'C') {
            T000_STARTBR_CITALT_CI();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITALT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B080_01_OUT_TITLE();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "CI-ALT NOT EXIST");
        }
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && SCCMPAG.FUNC != 'E') {
            B080_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_CITALT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITALT();
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
        IBS.init(SCCGWA, CICOALTL);
        CICOALTL.DATA.ALT_NO = CIRALT.KEY.ALT_NO;
        CICOALTL.DATA.ENTY_TYP = CIRALT.ENTY_TYP;
        CICOALTL.DATA.ENTY_NO = CIRALT.ENTY_NO;
        CICOALTL.DATA.ALT_TYP = CIRALT.ALT_TYP;
        CICOALTL.DATA.MSG_CHNL = CIRALT.MSG_CHNL;
        CICOALTL.DATA.ALT_LVL = CIRALT.ALT_LVL;
        CICOALTL.DATA.MAX_CNT = CIRALT.MAX_CNT;
        CICOALTL.DATA.EFF_DT = CIRALT.EFF_DT;
        CICOALTL.DATA.EXP_DT = CIRALT.EXP_DT;
        CICOALTL.DATA.REMARK = CIRALT.REMARK;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, CICOALTL);
        SCCMPAG.DATA_LEN = 199;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.FMT_ID_LEN = 268;
        BPCPNHIS.INFO.CI_NO = CIRALT.ENTY_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRALTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRALTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_TO_TBL() throws IOException,SQLException,Exception {
        CIRALT.KEY.ALT_NO = CICMALT.DATA.ALT_NO;
        CIRALT.ENTY_TYP = CICMALT.DATA.ENTY_TYP;
        CIRALT.ENTY_NO = CICMALT.DATA.ENTY_NO;
        CEP.TRC(SCCGWA, CICMALT.DATA.ENTY_NO);
        CEP.TRC(SCCGWA, CIRALT.ENTY_NO);
        CIRALT.ALT_TYP = CICMALT.DATA.ALT_TYP;
        CIRALT.MSG_CHNL = CICMALT.DATA.MSG_CHNL;
        CIRALT.ALT_LVL = CICMALT.DATA.ALT_LVL;
        CIRALT.MAX_CNT = CICMALT.DATA.MAX_CNT;
        CIRALT.EFF_DT = CICMALT.DATA.EFF_DT;
        CIRALT.EXP_DT = CICMALT.DATA.EXP_DT;
        CIRALT.REMARK = CICMALT.DATA.REMARK;
        if (CICMALT.FUNC == 'A') {
            CIRALT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRALT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRALT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        }
        CIRALT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRALT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRALT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, CIRALT);
    }
    public void R000_DATA_TRANS_TO_FMT() throws IOException,SQLException,Exception {
        CICOALTO.ALT_NO = CIRALT.KEY.ALT_NO;
        CICOALTO.ENTY_TYP = CIRALT.ENTY_TYP;
        CICOALTO.ENTY_NO = CIRALT.ENTY_NO;
        CICOALTO.ALT_TYP = CIRALT.ALT_TYP;
        CICOALTO.MSG_CHNL = CIRALT.MSG_CHNL;
        CICOALTO.ALT_LVL = CIRALT.ALT_LVL;
        CICOALTO.MAX_CNT = CIRALT.MAX_CNT;
        CICOALTO.EFF_DT = CIRALT.EFF_DT;
        CICOALTO.EXP_DT = CIRALT.EXP_DT;
        CICOALTO.REMARK = CIRALT.REMARK;
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (CICMALT.FUNC == 'A' 
            || CICMALT.FUNC == 'M' 
            || CICMALT.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = CICOALTO;
        SCCFMT.DATA_LEN = 199;
        if (CICMALT.FUNC == 'B') {
            SCCFMT.DATA_PTR = CICOALTL;
            SCCFMT.DATA_LEN = 199;
        }
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.READ(SCCGWA, CIRALT, CITALT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ALT_INF_NOTFND);
        }
    }
    public void T000_READ_CITALT_EXIST() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.READ(SCCGWA, CIRALT, CITALT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ALT_INF_EXIST);
        }
    }
    public void T000_READ_CITBAS_UPDATE() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTEXIST);
        }
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_WRITE_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.WRITE(SCCGWA, CIRALT, CITALT_RD);
    }
    public void T000_READ_CITALT_UPD() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.upd = true;
        IBS.READ(SCCGWA, CIRALT, CITALT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ALT_INF_NOTFND);
        }
    }
    public void T000_REWRITE_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.REWRITE(SCCGWA, CIRALT, CITALT_RD);
    }
    public void T000_DELETE_CITALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        IBS.DELETE(SCCGWA, CIRALT, CITALT_RD);
    }
    public void T000_STARTBR_CITALT_CI() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CIRALT.ENTY_TYP);
        CEP.TRC(SCCGWA, CIRALT.ENTY_NO);
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO "
            + "AND ( EXP_DT >= :WS_AC_DATE "
            + "OR EXP_DT = 0 )";
        CITALT_BR.rp.order = "ALT_NO";
        IBS.STARTBR(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_STARTBR_CITALT_TYP() throws IOException,SQLException,Exception {
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO "
            + "AND ALT_TYP = :CIRALT.ALT_TYP "
            + "AND ( EXP_DT >= :WS_AC_DATE "
            + "OR EXP_DT = 0 )";
        CITALT_BR.rp.order = "ALT_NO";
        IBS.STARTBR(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_STARTBR_CITALT_ALT() throws IOException,SQLException,Exception {
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.where = "ALT_TYP = :CIRALT.ALT_TYP";
        CITALT_BR.rp.order = "ALT_NO";
        IBS.STARTBR(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_STARTBR_CITALT_CHNL() throws IOException,SQLException,Exception {
        CITALT_BR.rp = new DBParm();
        CITALT_BR.rp.TableName = "CITALT";
        CITALT_BR.rp.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO "
            + "AND MSG_CHNL = :CIRALT.MSG_CHNL "
            + "AND ( EXP_DT >= :WS_AC_DATE "
            + "OR EXP_DT = 0 )";
        CITALT_BR.rp.order = "ALT_NO";
        IBS.STARTBR(SCCGWA, CIRALT, this, CITALT_BR);
    }
    public void T000_READNEXT_CITALT_FIRST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRALT, this, CITALT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ALT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ALT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE CITALT ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITALT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_CITALT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRALT, this, CITALT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ALT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ALT_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE CITALT ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITALT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITALT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITALT_BR);
    }
    public void T000_COUNT_TYP() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.set = "WS-CNT=COUNT(*)";
        CITALT_RD.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO "
            + "AND ALT_TYP = :CIRALT.ALT_TYP";
        IBS.GROUP(SCCGWA, CIRALT, this, CITALT_RD);
    }
    public void T000_COUNT_ALT() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.set = "WS-CNT=COUNT(*)";
        CITALT_RD.where = "ALT_TYP = :CIRALT.ALT_TYP";
        IBS.GROUP(SCCGWA, CIRALT, this, CITALT_RD);
    }
    public void T000_COUNT_CHNL() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.set = "WS-CNT=COUNT(*)";
        CITALT_RD.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO "
            + "AND MSG_CHNL = :CIRALT.MSG_CHNL";
        IBS.GROUP(SCCGWA, CIRALT, this, CITALT_RD);
    }
    public void T000_COUNT_CI() throws IOException,SQLException,Exception {
        CITALT_RD = new DBParm();
        CITALT_RD.TableName = "CITALT";
        CITALT_RD.set = "WS-CNT=COUNT(*)";
        CITALT_RD.where = "ENTY_TYP = :CIRALT.ENTY_TYP "
            + "AND ENTY_NO = :CIRALT.ENTY_NO";
        IBS.GROUP(SCCGWA, CIRALT, this, CITALT_RD);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCSGSEQ.RC);
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID, WS_ERR_INFO);
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
        if (CICMALT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICMALT=");
            CEP.TRC(SCCGWA, CICMALT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
