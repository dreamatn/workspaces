package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class CIZSVER {
    int JIBS_tmp_int;
    CICOVERL_DATA DATA;
    DBParm CITVER_RD;
    DBParm CITBAS_RD;
    brParm CITVER_BR = new brParm();
    DBParm CITJRL_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT_11 = "CI011";
    String K_OUTPUT_FMTLST = "CI460";
    int K_MAX_ROW = 25;
    int K_MAX_GRL = 50;
    int K_MAX_VER = 500;
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_RMK = "CI ID CHECK INFO   ";
    String K_HIS_RMK_BAS = "CI VER-STSW UPDA   ";
    String K_HIS_CPY = "CIRVER";
    String K_HIS_CPY_BAS = "CIRBAS";
    String WS_MSGID = " ";
    String WS_ERR_INFO = " ";
    short WS_FLD_NO = 0;
    int WS_T = 0;
    int WS_I = 0;
    int WS_O = 0;
    int WS_Z = 0;
    int WS_CX = 0;
    int WS_PAGE_ROW = 0;
    short WS_RECORD_NUM = 0;
    char WS_BAS_FLG = ' ';
    char WS_VER_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    CICSSCH CICSSCH = new CICSSCH();
    CIRVER CIRVER0 = new CIRVER();
    CIRVER CIRVERN = new CIRVER();
    CIRVER CIRVER = new CIRVER();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRBAS CIRBAS = new CIRBAS();
    CICOSVER CICOSVER = new CICOSVER();
    CICOVERL CICOVERL = new CICOVERL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    CIRJRL CIRJRL = new CIRJRL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPORLO BPCPORLO = new BPCPORLO();
    SCCBINF SCCBINF = new SCCBINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSVER CICSVER;
    public void MP(SCCGWA SCCGWA, CICSVER CICSVER) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSVER = CICSVER;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSVER return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        S000_CALL_SCZGJRN();
        if (pgmRtn) return;
        GWA_BP_AREA.NFHIS_CUR_SEQ = 0;
    } //FROM #ENDIF
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (CICSVER.FUNC == 'I') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B010_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (CICSVER.FUNC == 'M') {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICSVER.FUNC == 'B') {
            B050_BRW_BR_LVL();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + CICSVER.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B030_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, CIRVER);
        IBS.init(SCCGWA, CIRVER0);
        IBS.init(SCCGWA, CIRVERN);
        CIRVER.KEY.CI_NO = CICSVER.CI_DATE.CI_NO;
        T000_READ_CITVER_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRVER, CIRVER0);
        if (WS_VER_FLG == 'F') {
            if (CIRVER.REAL_CHK == '1') {
                if (CICSVER.CI_DATE.REAL_CHK == '2') {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_REAL_CHK_NOT_CHANGE);
                }
            }
        }
        CIRVER.REAL_CHK = CICSVER.CI_DATE.REAL_CHK;
        CIRVER.IDT_CHK = CICSVER.CI_DATE.IDT_CHK;
        CIRVER.RSN = CICSVER.CI_DATE.RSN;
        CIRVER.DEAL_WAY = IBS.CLS2CPY(SCCGWA, CICSVER.CI_DATE.DEAL_WAY);
        CIRVER.DESC = CICSVER.CI_DATE.DESC;
        CIRVER.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRVER.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRVER.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, CIRVER.KEY.CI_NO);
        CEP.TRC(SCCGWA, CIRVER.REAL_CHK);
        CEP.TRC(SCCGWA, CICSVER.CI_DATE.IDT_CHK);
        CEP.TRC(SCCGWA, CIRVER.IDT_CHK);
        CEP.TRC(SCCGWA, CIRVER.RSN);
        CEP.TRC(SCCGWA, CIRVER.DEAL_WAY);
        CEP.TRC(SCCGWA, CIRVER.DESC);
        if (WS_VER_FLG == 'N') {
            CIRVER.OPEN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            CIRVER.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRVER.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRVER.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_WRITE_CITVER();
            if (pgmRtn) return;
            BPCPNHIS.INFO.TX_TYP = 'A';
        } else {
            BPCPNHIS.INFO.TX_TYP = 'M';
            T000_REWRITE_CITVER();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, CIRVER, CIRVERN);
        R000_WRITE_HIS_PROC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSVER.CI_DATE.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
            if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
            JIBS_tmp_int = CIRBAS.VER_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
            CIRBAS.VER_STSW = CIRBAS.VER_STSW.substring(0, 20 - 1) + "0" + CIRBAS.VER_STSW.substring(20 + 1 - 1);
            if (CICSVER.CI_DATE.REAL_CHK == '1') {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = CIRBAS.VER_STSW.substring(0, 20 - 1) + "1" + CIRBAS.VER_STSW.substring(20 + 1 - 1);
            }
            if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("01")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "1000000000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("02")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0100000000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("03")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0010000000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("04")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0001000000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("05")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000100000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("06")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000010000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("07")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000001000000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("08")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000000100000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("09")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000000010000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("10")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000000001000000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("11")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000000000100000000" + CIRBAS.VER_STSW.substring(19);
            } else if (CICSVER.CI_DATE.IDT_CHK.equalsIgnoreCase("99")) {
                if (CIRBAS.VER_STSW == null) CIRBAS.VER_STSW = "";
                JIBS_tmp_int = CIRBAS.VER_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.VER_STSW += " ";
                CIRBAS.VER_STSW = "0000000000010000000" + CIRBAS.VER_STSW.substring(19);
            } else {
            }
            T000_REWRITE_CITBAS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRJRL);
            CIRJRL.KEY.JCI_NO = CICSVER.CI_DATE.CI_NO;
            CEP.TRC(SCCGWA, CIRJRL.KEY.JCI_NO);
            T000_READ_CITJRL_FIRST();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                Z_RET();
                if (pgmRtn) return;
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTEXIST);
            }
        }
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        BPCPNHIS.INFO.CI_NO = CICSVER.CI_DATE.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK_BAS;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY_BAS;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    R000_DAT_TRANS_TOFMT();
    if (pgmRtn) return;
    R000_DATA_OUTPUT_FMT();
    if (pgmRtn) return;
    public void B010_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRVER);
        CIRVER.KEY.CI_NO = CICSVER.CI_DATE.CI_NO;
        T000_READ_CITVER();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_RECORD_NOTFND);
        }
        CIRBAS.KEY.CI_NO = CICSVER.CI_DATE.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CIRVER.KEY.CI_NO);
        R000_DAT_TRANS_TOFMT();
        if (pgmRtn) return;
        R000_DATA_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void B050_BRW_BR_LVL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        IBS.init(SCCGWA, CIRVER);
        IBS.init(SCCGWA, CICOVERL.OUTPUT_TITLE);
        BPCPQORG.BR = CICSVER.CI_DATE.BR;
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        S00_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.LVL);
        if (BPCPQORG.LVL == '2') {
            CIRVER.OPEN_BR = CICSVER.CI_DATE.BR;
            CIRVER.IDT_CHK = CICSVER.CI_DATE.IDT_CHK;
            CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'N';
            T000_STARTBR_CITVER_BR();
            if (pgmRtn) return;
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCPQORG.LVL == '9') {
            CIRVER.IDT_CHK = CICSVER.CI_DATE.IDT_CHK;
            CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'N';
            T000_STARTBR_CITVER();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CIRVER.IDT_CHK);
            B060_BROWSE_PROC();
            if (pgmRtn) return;
        } else if ((BPCPQORG.LVL == '4' 
                || BPCPQORG.LVL == '6')) {
            IBS.init(SCCGWA, BPCPORLO);
            BPCPORLO.BNK = SCCGWA.COMM_AREA.TR_BANK;
            BPCPORLO.BR = CICSVER.CI_DATE.BR;
            S00_CALL_BPZPORLO();
            if (pgmRtn) return;
            for (WS_I = 1; BPCPORLO.SUB_BR_DATA[WS_I-1].SUB_BR != ' ' 
                && WS_I <= K_MAX_GRL; WS_I += 1) {
                if (BPCPORLO.FLAG == 'Y') {
                    CICSVER.CI_DATE.BR = BPCPORLO.SUB_BR_DATA[WS_I-1].SUB_BR;
                    CIRVER.OPEN_BR = CICSVER.CI_DATE.BR;
                    CIRVER.IDT_CHK = CICSVER.CI_DATE.IDT_CHK;
                    CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'N';
                    T000_STARTBR_CITVER_BR();
                    if (pgmRtn) return;
                    B060_BROWSE_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID PQORG-LVL(" + BPCPQORG.LVL + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B060_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (CICSVER.CI_DATE.PAGE_ROW > K_MAX_ROW 
            || CICSVER.CI_DATE.PAGE_ROW == 0) {
            WS_PAGE_ROW = K_MAX_ROW;
        } else {
            WS_PAGE_ROW = CICSVER.CI_DATE.PAGE_ROW;
        }
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        T000_READNEXT_CITVER();
        if (pgmRtn) return;
        if (CICSVER.CI_DATE.PAGE_NUM > 0) {
            WS_CX = ( CICSVER.CI_DATE.PAGE_NUM - 1 ) * WS_PAGE_ROW + 1;
            B020_20_CURR_PAGE();
            if (pgmRtn) return;
        } else {
            B020_10_TOTAL_PAGE();
            if (pgmRtn) return;
        }
        R000_DATA_OUTPUT_FMTLST();
        if (pgmRtn) return;
    }
    public void B020_10_TOTAL_PAGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "TOTAL-PAGE");
        CEP.TRC(SCCGWA, "SVER-OP-DOWNL:");
        CEP.TRC(SCCGWA, CICSVER.CI_DATE.OP_DOWNL);
        if (CICSVER.CI_DATE.OP_DOWNL == 'Y') {
            while (WS_VER_FLG != 'N') {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRVER.KEY.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                B020_10_OUT_BRW_DATA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRVER);
                T000_READNEXT_CITVER();
                if (pgmRtn) return;
            }
        } else {
            while (WS_VER_FLG != 'N' 
                && WS_Z < K_MAX_VER) {
                CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'N';
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRVER.KEY.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                B020_10_OUT_BRW_DATA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRVER);
                T000_READNEXT_CITVER();
                if (pgmRtn) return;
                if (WS_Z == K_MAX_VER) {
                    CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'Y';
                    WS_MSGID = CICMSG_ERROR_MSG.CI_OVER_ROW_LIMIT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        T000_ENDBR_CITVER();
        if (pgmRtn) return;
        B020_01_OUT_PAGE_TITLE();
        if (pgmRtn) return;
    }
    public void B020_20_CURR_PAGE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "CURR-PAGE");
        CEP.TRC(SCCGWA, "SVER-OPEN-DOWNL");
        if (CICSVER.CI_DATE.OP_DOWNL == 'Y') {
            for (WS_T = 1; WS_O <= WS_PAGE_ROW 
                && WS_VER_FLG != 'N'; WS_T += 1) {
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRVER.KEY.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                B020_20_OUT_BRW_DATA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRVER);
                T000_READNEXT_CITVER();
                if (pgmRtn) return;
            }
        } else {
            for (WS_T = 1; WS_O <= WS_PAGE_ROW 
                && WS_VER_FLG != 'N' 
                && WS_Z < K_MAX_VER; WS_T += 1) {
                CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'N';
                IBS.init(SCCGWA, CIRBAS);
                CIRBAS.KEY.CI_NO = CIRVER.KEY.CI_NO;
                T000_READ_CITBAS();
                if (pgmRtn) return;
                B020_20_OUT_BRW_DATA();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRVER);
                T000_READNEXT_CITVER();
                if (pgmRtn) return;
                if (WS_Z == K_MAX_VER) {
                    CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'Y';
                    WS_MSGID = CICMSG_ERROR_MSG.CI_OVER_ROW_LIMIT;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        T000_ENDBR_CITVER();
        if (pgmRtn) return;
    }
    public void B020_01_OUT_PAGE_TITLE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUT-PAGE-TITLE");
        CEP.TRC(SCCGWA, WS_O);
        CEP.TRC(SCCGWA, WS_Z);
        CICOVERL.OUTPUT_TITLE.TOTAL_NUM = WS_Z;
        CEP.TRC(SCCGWA, "3");
        CEP.TRC(SCCGWA, WS_PAGE_ROW);
        CEP.TRC(SCCGWA, CICOVERL.OUTPUT_TITLE.TOTAL_NUM);
        CEP.TRC(SCCGWA, CICOVERL.OUTPUT_TITLE.TOTAL_PAGE);
        CEP.TRC(SCCGWA, WS_RECORD_NUM);
        WS_RECORD_NUM = (short) (CICOVERL.OUTPUT_TITLE.TOTAL_NUM % WS_PAGE_ROW);
        CICOVERL.OUTPUT_TITLE.TOTAL_PAGE = (short) ((CICOVERL.OUTPUT_TITLE.TOTAL_NUM - WS_RECORD_NUM) / WS_PAGE_ROW);
        if (WS_RECORD_NUM > 0) {
            CICOVERL.OUTPUT_TITLE.TOTAL_PAGE = (short) (CICOVERL.OUTPUT_TITLE.TOTAL_PAGE + 1);
        }
        if (WS_O >= WS_Z) {
            CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'Y';
        } else {
            CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'N';
        }
    }
    public void B020_10_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        if (WS_O < WS_PAGE_ROW) {
            WS_Z = WS_Z + 1;
            WS_O = WS_O + 1;
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.CI_NO = CIRVER.KEY.CI_NO;
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.ID_TYP = CIRBAS.ID_TYPE;
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.ID_NO = CIRBAS.ID_NO;
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.CI_NM = CIRBAS.CI_NM;
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.REAL_CHK = CIRVER.REAL_CHK;
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.IDT_CHK = CIRVER.IDT_CHK;
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.RSN = CIRVER.RSN;
            CICOVERL.DATA.set(WS_O-1, DATA);
            if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
            JIBS_tmp_int = CIRVER.DEAL_WAY.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DAL_WAY1 = CIRVER.DEAL_WAY.substring(0, 1).charAt(0);
            CICOVERL.DATA.set(WS_O-1, DATA);
            if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
            JIBS_tmp_int = CIRVER.DEAL_WAY.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DAL_WAY2 = CIRVER.DEAL_WAY.substring(2 - 1, 2 + 1 - 1).charAt(0);
            CICOVERL.DATA.set(WS_O-1, DATA);
            if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
            JIBS_tmp_int = CIRVER.DEAL_WAY.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DAL_WAY3 = CIRVER.DEAL_WAY.substring(3 - 1, 3 + 1 - 1).charAt(0);
            CICOVERL.DATA.set(WS_O-1, DATA);
            if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
            JIBS_tmp_int = CIRVER.DEAL_WAY.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DAL_WAY4 = CIRVER.DEAL_WAY.substring(4 - 1, 4 + 1 - 1).charAt(0);
            CICOVERL.DATA.set(WS_O-1, DATA);
            if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
            JIBS_tmp_int = CIRVER.DEAL_WAY.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DAL_WAY5 = CIRVER.DEAL_WAY.substring(5 - 1, 5 + 1 - 1).charAt(0);
            CICOVERL.DATA.set(WS_O-1, DATA);
            if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
            JIBS_tmp_int = CIRVER.DEAL_WAY.length();
            for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DAL_WAY6 = CIRVER.DEAL_WAY.substring(6 - 1, 6 + 1 - 1).charAt(0);
            CICOVERL.DATA.set(WS_O-1, DATA);
            DATA = CICOVERL.DATA.get(WS_O-1);
            DATA.DESC = CIRVER.DESC;
            CICOVERL.DATA.set(WS_O-1, DATA);
        } else {
            WS_Z = WS_Z + 1;
        }
        CICOVERL.OUTPUT_TITLE.TOTAL_NUM = WS_Z;
        CICOVERL.OUTPUT_TITLE.CURR_PAGE = 1;
        CICOVERL.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_O;
        DATA = new CICOVERL_DATA();
        CICOVERL.DATA.add(DATA);
    }
    public void B020_20_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        WS_Z = WS_Z + 1;
        if (WS_Z >= WS_CX) {
            WS_O = WS_O + 1;
            if (WS_O <= WS_PAGE_ROW) {
                CICOVERL.OUTPUT_TITLE.CURR_PAGE = (short) CICSVER.CI_DATE.PAGE_NUM;
                CICOVERL.OUTPUT_TITLE.CURR_PAGE_ROW = (short) WS_O;
                DATA = new CICOVERL_DATA();
                CICOVERL.DATA.add(DATA);
                DATA.CI_NO = CIRVER.KEY.CI_NO;
                DATA.ID_TYP = CIRBAS.ID_TYPE;
                DATA.ID_NO = CIRBAS.ID_NO;
                DATA.CI_NM = CIRBAS.CI_NM;
                DATA.REAL_CHK = CIRVER.REAL_CHK;
                DATA.IDT_CHK = CIRVER.IDT_CHK;
                DATA.RSN = CIRVER.RSN;
                if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
                JIBS_tmp_int = CIRVER.DEAL_WAY.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
                DATA.DAL_WAY1 = CIRVER.DEAL_WAY.substring(0, 1).charAt(0);
                if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
                JIBS_tmp_int = CIRVER.DEAL_WAY.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
                DATA.DAL_WAY2 = CIRVER.DEAL_WAY.substring(2 - 1, 2 + 1 - 1).charAt(0);
                if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
                JIBS_tmp_int = CIRVER.DEAL_WAY.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
                DATA.DAL_WAY3 = CIRVER.DEAL_WAY.substring(3 - 1, 3 + 1 - 1).charAt(0);
                if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
                JIBS_tmp_int = CIRVER.DEAL_WAY.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
                DATA.DAL_WAY4 = CIRVER.DEAL_WAY.substring(4 - 1, 4 + 1 - 1).charAt(0);
                if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
                JIBS_tmp_int = CIRVER.DEAL_WAY.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
                DATA.DAL_WAY5 = CIRVER.DEAL_WAY.substring(5 - 1, 5 + 1 - 1).charAt(0);
                if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
                JIBS_tmp_int = CIRVER.DEAL_WAY.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
                DATA.DAL_WAY6 = CIRVER.DEAL_WAY.substring(6 - 1, 6 + 1 - 1).charAt(0);
                DATA.DESC = CIRVER.DESC;
            }
        }
    }
    public void R000_DAT_TRANS_TOFMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICOSVER);
        CICOSVER.CI_NO = CIRVER.KEY.CI_NO;
        CICOSVER.ID_TYP = CIRBAS.ID_TYPE;
        CICOSVER.ID_NO = CIRBAS.ID_NO;
        CICOSVER.CI_NM = CIRBAS.CI_NM;
        CICOSVER.REAL_CHK = CIRVER.REAL_CHK;
        CICOSVER.IDT_CHK = CIRVER.IDT_CHK;
        CICOSVER.RSN = CIRVER.RSN;
        if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
        JIBS_tmp_int = CIRVER.DEAL_WAY.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
        CICOSVER.DAL_WAY1 = CIRVER.DEAL_WAY.substring(0, 1).charAt(0);
        if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
        JIBS_tmp_int = CIRVER.DEAL_WAY.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
        CICOSVER.DAL_WAY2 = CIRVER.DEAL_WAY.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
        JIBS_tmp_int = CIRVER.DEAL_WAY.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
        CICOSVER.DAL_WAY3 = CIRVER.DEAL_WAY.substring(3 - 1, 3 + 1 - 1).charAt(0);
        if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
        JIBS_tmp_int = CIRVER.DEAL_WAY.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
        CICOSVER.DAL_WAY4 = CIRVER.DEAL_WAY.substring(4 - 1, 4 + 1 - 1).charAt(0);
        if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
        JIBS_tmp_int = CIRVER.DEAL_WAY.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
        CICOSVER.DAL_WAY5 = CIRVER.DEAL_WAY.substring(5 - 1, 5 + 1 - 1).charAt(0);
        if (CIRVER.DEAL_WAY == null) CIRVER.DEAL_WAY = "";
        JIBS_tmp_int = CIRVER.DEAL_WAY.length();
        for (int i=0;i<20-JIBS_tmp_int;i++) CIRVER.DEAL_WAY += " ";
        CICOSVER.DAL_WAY6 = CIRVER.DEAL_WAY.substring(6 - 1, 6 + 1 - 1).charAt(0);
        CICOSVER.DESC = CIRVER.DESC;
        CEP.TRC(SCCGWA, CICOSVER.CI_NO);
        CEP.TRC(SCCGWA, CICOSVER.ID_TYP);
        CEP.TRC(SCCGWA, CICOSVER.ID_NO);
        CEP.TRC(SCCGWA, CICOSVER.CI_NM);
        CEP.TRC(SCCGWA, CICOSVER.REAL_CHK);
        CEP.TRC(SCCGWA, CICOSVER.IDT_CHK);
    }
    public void R000_DATA_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_11;
        SCCFMT.DATA_PTR = CICOSVER;
        SCCFMT.DATA_LEN = 592;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_DATA_OUTPUT_FMTLST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMTLST;
        SCCFMT.DATA_PTR = CICOVERL;
        SCCFMT.DATA_LEN = 29619;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.CI_NO = CICSVER.CI_DATE.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.TX_RMK = K_HIS_RMK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_CPY;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRVER0;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRVERN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_CITVER_UPD() throws IOException,SQLException,Exception {
        CITVER_RD = new DBParm();
        CITVER_RD.TableName = "CITVER";
        CITVER_RD.upd = true;
        IBS.READ(SCCGWA, CIRVER, CITVER_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VER_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VER_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITVER";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITVER() throws IOException,SQLException,Exception {
        CITVER_RD = new DBParm();
        CITVER_RD.TableName = "CITVER";
        IBS.READ(SCCGWA, CIRVER, CITVER_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VER_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VER_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITVER";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_CITVER() throws IOException,SQLException,Exception {
        CITVER_RD = new DBParm();
        CITVER_RD.TableName = "CITVER";
        IBS.REWRITE(SCCGWA, CIRVER, CITVER_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VER_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VER_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITVER";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_CITVER() throws IOException,SQLException,Exception {
        CITVER_RD = new DBParm();
        CITVER_RD.TableName = "CITVER";
        IBS.WRITE(SCCGWA, CIRVER, CITVER_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VER_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VER_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITVER";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.col = "ID_TYPE, ID_NO, CI_NM";
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BAS_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BAS_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITBAS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITBAS_UPD() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        CITBAS_RD.upd = true;
        IBS.READ(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_REWRITE_CITBAS() throws IOException,SQLException,Exception {
        CITBAS_RD = new DBParm();
        CITBAS_RD.TableName = "CITBAS";
        IBS.REWRITE(SCCGWA, CIRBAS, CITBAS_RD);
    }
    public void T000_STARTBR_CITVER() throws IOException,SQLException,Exception {
        CITVER_BR.rp = new DBParm();
        CITVER_BR.rp.TableName = "CITVER";
        CITVER_BR.rp.where = "IDT_CHK = :CIRVER.IDT_CHK";
        IBS.STARTBR(SCCGWA, CIRVER, this, CITVER_BR);
    }
    public void T000_STARTBR_CITVER_BR() throws IOException,SQLException,Exception {
        CITVER_BR.rp = new DBParm();
        CITVER_BR.rp.TableName = "CITVER";
        CITVER_BR.rp.where = "IDT_CHK = :CIRVER.IDT_CHK "
            + "AND OPEN_BR = :CIRVER.OPEN_BR";
        IBS.STARTBR(SCCGWA, CIRVER, this, CITVER_BR);
    }
    public void T000_READNEXT_CITVER() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRVER, this, CITVER_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VER_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CICOVERL.OUTPUT_TITLE.LAST_PAGE = 'Y';
            WS_VER_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE CITVER ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITVER";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_CITVER() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITVER_BR);
    }
    public void T000_READ_CITJRL_FIRST() throws IOException,SQLException,Exception {
        CITJRL_RD = new DBParm();
        CITJRL_RD.TableName = "CITJRL";
        CITJRL_RD.where = "JCI_NO = :CIRJRL.KEY.JCI_NO";
        CITJRL_RD.fst = true;
        CITJRL_RD.order = "TS";
        IBS.READ(SCCGWA, CIRJRL, this, CITJRL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void S00_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        CEP.TRC(SCCGWA, BPCPQORG.RC.RC_CODE);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S00_CALL_BPZPORLO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG-LOW", BPCPORLO);
        CEP.TRC(SCCGWA, BPCPORLO.RC.RC_CODE);
        if (BPCPORLO.RC.RC_CODE != 0) {
            WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPORLO.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_CIZSSCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-SEARCH-CI  ", CICSSCH);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_MSGID);
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
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
        if (CICSVER.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "CICSVER=");
            CEP.TRC(SCCGWA, CICSVER);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
