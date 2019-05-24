package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFPREA {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_MGM_GRPL = "BP-R-MGM-GRPL       ";
    String CPN_R_MGM_TATH = "BP-R-MGM-TATH       ";
    String CPN_F_OTS_ROL_CHK = "BP-F-OTS-ROL-CHK    ";
    String CPN_F_OTS_TATH_CHK = "BP-F-OTS-TATH-CHK   ";
    String CPN_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_R_MGM_GPLB = "BP-R-MGM-GPLB       ";
    String CPN_R_MGM_TATB = "BP-R-MGM-TATB       ";
    char WS_ATH_TYP = ' ';
    int WS_SUPR_BR = 0;
    short WS_BR_LENG = 0;
    short WS_TLR_LENG = 0;
    short WS_I = 0;
    char WS_TBL_GRP_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPRTATH BPRTATH = new BPRTATH();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    BPCRTATH BPCRTATH = new BPCRTATH();
    BPCRGPLB BPCRGPLB = new BPCRGPLB();
    BPCRTATB BPCRTATB = new BPCRTATB();
    BPCFOTRO BPCFOTRO = new BPCFOTRO();
    BPCFOTTA BPCFOTTA = new BPCFOTTA();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCFPREA BPCFPREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFPREA BPCFPREA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFPREA = BPCFPREA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFPREA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRGRPL);
        IBS.init(SCCGWA, BPRTATH);
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPCRGPLB);
        IBS.init(SCCGWA, BPCRTATB);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFPREA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFPREA);
        WS_BR_LENG = 6;
        WS_TLR_LENG = 8;
        if (BPCFPREA.ASS_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FPREA_ASS_ID, BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFPREA.ASSTYP == 'T') {
            IBS.init(SCCGWA, BPCFTLRQ);
            if (BPCFPREA.ASS_ID == null) BPCFPREA.ASS_ID = "";
            JIBS_tmp_int = BPCFPREA.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCFPREA.ASS_ID += " ";
            BPCFTLRQ.INFO.TLR = BPCFPREA.ASS_ID.substring(0, WS_TLR_LENG);
            S000_CALL_BPZFTLRQ();
            if (pgmRtn) return;
            WS_SUPR_BR = BPCFTLRQ.INFO.NEW_BR;
        } else if (BPCFPREA.ASSTYP == 'O') {
            IBS.init(SCCGWA, BPCPQORG);
            if (BPCFPREA.ASS_ID == null) BPCFPREA.ASS_ID = "";
            JIBS_tmp_int = BPCFPREA.ASS_ID.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) BPCFPREA.ASS_ID += " ";
            if (BPCFPREA.ASS_ID.substring(0, WS_BR_LENG).trim().length() == 0) BPCPQORG.BR = 0;
            else BPCPQORG.BR = Integer.parseInt(BPCFPREA.ASS_ID.substring(0, WS_BR_LENG));
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            WS_SUPR_BR = BPCPQORG.SUPR_BR;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_FPREA_ASSTYP, BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_ERASE_OPER_PRIV() throws IOException,SQLException,Exception {
        WS_ATH_TYP = '0';
        R_ERASE_GRPL();
        if (pgmRtn) return;
        R_ERASE_TATH();
        if (pgmRtn) return;
    }
    public void B030_ERASE_AUTH_PRIV() throws IOException,SQLException,Exception {
        WS_ATH_TYP = '1';
        R_ERASE_GRPL();
        if (pgmRtn) return;
        R_ERASE_TATH();
        if (pgmRtn) return;
    }
    public void R_ERASE_GRPL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRGRPL);
        BPCRGRPL.INFO.FUNC = 'R';
        BPRGRPL.KEY.ASS_TYP = BPCFPREA.ASSTYP;
        BPRGRPL.KEY.ASS_ID = BPCFPREA.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = WS_ATH_TYP;
        S000_CALL_BPZRGRPL();
        if (pgmRtn) return;
        if (BPCRGRPL.RETURN_INFO == 'F') {
            for (WS_I = 1; WS_I <= BPRGRPL.ROLE_CNT; WS_I += 1) {
                if (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '0' 
                    || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '2' 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '1' 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_STS == '3' 
                    && (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                    IBS.init(SCCGWA, BPCFOTRO);
                    BPCFOTRO.ASSTYP = 'O';
                    BPCFOTRO.ASS_ID = "" + WS_SUPR_BR;
                    JIBS_tmp_int = BPCFOTRO.ASS_ID.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCFOTRO.ASS_ID = "0" + BPCFOTRO.ASS_ID;
                    BPCFOTRO.ATH_TYP = WS_ATH_TYP;
                    BPCFOTRO.ROLE_CD = BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].ROLE_CD;
                    S000_CALL_BPZFOTRO();
                    if (pgmRtn) return;
                    if (BPCFOTRO.PRIV_FLG == 'N') {
                        BPRGRPL.REDEFINES14.REDEFINES16.ROLE_INFO[WS_I-1].EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRGRPL.REDEFINES14.ROLE_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRGRPL.REDEFINES14.REDEFINES16);
                        BPRGRPL.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRGRPL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    }
                }
            }
            IBS.init(SCCGWA, BPCRGRPL);
            BPCRGRPL.INFO.FUNC = 'U';
            S000_CALL_BPZRGRPL();
            if (pgmRtn) return;
        }
    }
    public void R_ERASE_TATH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRTATH);
        IBS.init(SCCGWA, BPRTATH);
        BPCRTATH.INFO.FUNC = 'R';
        BPRTATH.KEY.ASS_TYP = BPCFPREA.ASSTYP;
        BPRTATH.KEY.ASS_ID = BPCFPREA.ASS_ID;
        BPRTATH.KEY.ATH_TYP = WS_ATH_TYP;
        S000_CALL_BPZRTATH();
        if (pgmRtn) return;
        if (BPCRTATH.RETURN_INFO == 'F') {
            for (WS_I = 1; WS_I <= BPRTATH.TXIF_CNT; WS_I += 1) {
                if (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EFF_DT <= SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT > SCCGWA.COMM_AREA.AC_DATE 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '0' 
                    || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '2' 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '1' 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM < SCCGWA.COMM_AREA.TR_TIME)) 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT > SCCGWA.COMM_AREA.AC_DATE) 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_STS == '3' 
                    && (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT > SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EFF_TM > SCCGWA.COMM_AREA.TR_TIME)) 
                    || BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT < SCCGWA.COMM_AREA.AC_DATE 
                    || (BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_DT == SCCGWA.COMM_AREA.AC_DATE 
                    && BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].MOV_EXP_TM < SCCGWA.COMM_AREA.TR_TIME)))) {
                    IBS.init(SCCGWA, BPCFOTTA);
                    BPCFOTTA.ASSTYP = 'O';
                    BPCFOTTA.ASS_ID = "" + WS_SUPR_BR;
                    JIBS_tmp_int = BPCFOTTA.ASS_ID.length();
                    for (int i=0;i<6-JIBS_tmp_int;i++) BPCFOTTA.ASS_ID = "0" + BPCFOTTA.ASS_ID;
                    BPCFOTTA.ATH_TYP = WS_ATH_TYP;
                    BPCFOTTA.IN_FLG = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].IN_FLG;
                    BPCFOTTA.SYS_MMO = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].SYS_MMO;
                    BPCFOTTA.TX_CD = BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].TX_CD;
                    S000_CALL_BPZFOTTA();
                    if (pgmRtn) return;
                    if (BPCFOTTA.PRIV_FLG == 'N') {
                        BPRTATH.REDEFINES14.REDEFINES16.TXIF_INFO[WS_I-1].EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRTATH.REDEFINES14.TXIF_TXT_TEXT1 = IBS.CLS2CPY(SCCGWA, BPRTATH.REDEFINES14.REDEFINES16);
                        BPRTATH.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                        BPRTATH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    }
                }
            }
            IBS.init(SCCGWA, BPCRTATH);
            BPCRTATH.INFO.FUNC = 'U';
            S000_CALL_BPZRTATH();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        CEP.TRC(SCCGWA, BPRGRPL.BLOB_ROLE_TXT.trim().length());
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL       ", BPCRGRPL);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTATH() throws IOException,SQLException,Exception {
        BPCRTATH.INFO.POINTER = BPRTATH;
        BPCRTATH.INFO.LEN = 52;
        CEP.TRC(SCCGWA, BPRTATH.BLOB_TXIF_TXT.trim().length());
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATH       ", BPCRTATH);
        if (BPCRTATH.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTATH.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFOTRO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-OTS-ROL-CHK    ", BPCFOTRO);
        if (BPCFOTRO.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTRO.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFOTTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-OTS-TATH-CHK   ", BPCFOTTA);
        if (BPCFOTTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFOTTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-TLR-INF-QUERY  ", BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG        ", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRGPLB() throws IOException,SQLException,Exception {
        BPCRGPLB.INFO.POINTER = BPRGRPL;
        BPCRGPLB.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GPLB       ", BPCRGPLB);
        if (BPCRGPLB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRGPLB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTATB() throws IOException,SQLException,Exception {
        BPCRTATB.INFO.POINTER = BPRTATH;
        BPCRTATB.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-TATB       ", BPCRTATB);
        if (BPCRTATB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRTATB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFPREA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFPREA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFPREA = ");
            CEP.TRC(SCCGWA, BPCFPREA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
