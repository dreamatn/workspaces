package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZTTHIB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    String JIBS_f0;
    brParm BPTTHIS_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZTTHIB";
    String K_TBL_THIS = "BPTTHIS ";
    String WS_TEMP_RECORD = " ";
    int WS_DATE = 0;
    int WS_END_DATE = 0;
    char WS_TBL_THIS_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPRTHIS BPRTHIS = new BPRTHIS();
    SCCGWA SCCGWA;
    BPCTTHIB BPCTTHIB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCTTHIB BPCTTHIB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCTTHIB = BPCTTHIB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZTTHIB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        WS_DATE = BPCTTHIB.DATE;
        WS_END_DATE = BPCTTHIB.END_DATE;
        if (BPCTTHIB.FUNC == 'S') {
            CEP.TRC(SCCGWA, "DEV1");
            BPRTHIS.AC = BPCTTHIB.AC;
            BPRTHIS.KEY.VCH_NO = BPCTTHIB.VCH_NO;
            BPRTHIS.AP_CODE = BPCTTHIB.AP_CODE;
            BPRTHIS.TLR = BPCTTHIB.TLR;
            BPRTHIS.BR = BPCTTHIB.BR;
            B010_STARTBR_PROC();
            if (pgmRtn) return;
        } else if (BPCTTHIB.FUNC == 'A') {
            BPRTHIS.KEY.DATE = BPCTTHIB.DATE;
            BPRTHIS.TR_CODE = BPCTTHIB.TR_CODE;
            BPRTHIS.RCE_PBNO = BPCTTHIB.RCE_PBNO;
            BPRTHIS.PAY_PBNO = BPCTTHIB.PAY_PBNO;
            BPRTHIS.CCY = BPCTTHIB.CCY;
            BPRTHIS.BR = BPCTTHIB.BR;
            BPRTHIS.STS = BPCTTHIB.STS;
            B030_STARTBR_REC_PAY_PROC();
            if (pgmRtn) return;
        } else if (BPCTTHIB.FUNC == 'C') {
            CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
            CEP.TRC(SCCGWA, BPRTHIS.RCE_PBNO);
            CEP.TRC(SCCGWA, BPRTHIS.PAY_PBNO);
            CEP.TRC(SCCGWA, BPRTHIS.CCY);
            CEP.TRC(SCCGWA, BPRTHIS.BR);
            CEP.TRC(SCCGWA, BPRTHIS.STS);
            CEP.TRC(SCCGWA, BPRTHIS.TR_CODE);
            BPRTHIS.KEY.DATE = BPCTTHIB.DATE;
            BPRTHIS.RCE_PBNO = BPCTTHIB.RCE_PBNO;
            BPRTHIS.PAY_PBNO = BPCTTHIB.PAY_PBNO;
            BPRTHIS.CCY = BPCTTHIB.CCY;
            BPRTHIS.BR = BPCTTHIB.BR;
            BPRTHIS.STS = BPCTTHIB.STS;
            BPRTHIS.TR_CODE = BPCTTHIB.TR_CODE;
            B040_STARTBR_REC_PAY_PROC_CN();
            if (pgmRtn) return;
        } else if (BPCTTHIB.FUNC == 'R') {
            B020_READNEXT_PROC();
            if (pgmRtn) return;
            BPCTTHIB.DATE = BPRTHIS.KEY.DATE;
            BPCTTHIB.VCH_NO = BPRTHIS.KEY.VCH_NO;
            BPCTTHIB.SEQ = BPRTHIS.KEY.SEQ;
            BPCTTHIB.AP_CODE = BPRTHIS.AP_CODE;
            BPCTTHIB.TR_CODE = BPRTHIS.TR_CODE;
            BPCTTHIB.DC_FLG = BPRTHIS.DC_FLG;
            BPCTTHIB.RCE_PBNO = BPRTHIS.RCE_PBNO;
            BPCTTHIB.PAY_PBNO = BPRTHIS.PAY_PBNO;
            BPCTTHIB.AC_NAME = BPRTHIS.AC_NAME;
            BPCTTHIB.CCY = BPRTHIS.CCY;
            BPCTTHIB.CCY_TYP = BPRTHIS.CCY_TYP;
            BPCTTHIB.AC = BPRTHIS.AC;
            BPCTTHIB.AMT = BPRTHIS.AMT;
            BPCTTHIB.ID_TYP = BPRTHIS.ID_TYP;
            BPCTTHIB.IDNO = BPRTHIS.IDNO;
            BPCTTHIB.AGT_NAME = BPRTHIS.AGT_NAME;
            BPCTTHIB.AGT_IDTYP = BPRTHIS.AGT_IDTYP;
            BPCTTHIB.AGT_IDNO = BPRTHIS.AGT_IDNO;
            BPCTTHIB.CASH_NO = BPRTHIS.CASH_NO;
            BPCTTHIB.RMK = BPRTHIS.RMK;
            BPCTTHIB.BR = BPRTHIS.BR;
            BPCTTHIB.TLR = BPRTHIS.TLR;
            BPCTTHIB.SUP = BPRTHIS.SUP;
            BPCTTHIB.STS = BPRTHIS.STS;
            if (BPRTHIS.TS == null) BPRTHIS.TS = "";
            JIBS_tmp_int = BPRTHIS.TS.length();
            for (int i=0;i<26-JIBS_tmp_int;i++) BPRTHIS.TS += " ";
            JIBS_tmp_str[0] = "" + BPCTTHIB.TM;
            JIBS_f0 = "";
            for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCTTHIB.TM;
            JIBS_NumStr = BPRTHIS.TS.substring(12 - 1, 12 + 2 - 1) + JIBS_NumStr.substring(2);
            BPCTTHIB.TM = Integer.parseInt(JIBS_NumStr);
            if (BPRTHIS.TS == null) BPRTHIS.TS = "";
            JIBS_tmp_int = BPRTHIS.TS.length();
            for (int i=0;i<26-JIBS_tmp_int;i++) BPRTHIS.TS += " ";
            JIBS_tmp_str[0] = "" + BPCTTHIB.TM;
            JIBS_f0 = "";
            for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCTTHIB.TM;
            JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + BPRTHIS.TS.substring(15 - 1, 15 + 2 - 1) + JIBS_NumStr.substring(3 + 2 - 1);
            BPCTTHIB.TM = Integer.parseInt(JIBS_NumStr);
            if (BPRTHIS.TS == null) BPRTHIS.TS = "";
            JIBS_tmp_int = BPRTHIS.TS.length();
            for (int i=0;i<26-JIBS_tmp_int;i++) BPRTHIS.TS += " ";
            JIBS_tmp_str[0] = "" + BPCTTHIB.TM;
            JIBS_f0 = "";
            for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
            JIBS_NumStr = JIBS_f0 + BPCTTHIB.TM;
            JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + BPRTHIS.TS.substring(18 - 1, 18 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
            BPCTTHIB.TM = Integer.parseInt(JIBS_NumStr);
            CEP.TRC(SCCGWA, BPRTHIS.TS);
            CEP.TRC(SCCGWA, BPCTTHIB.TM);
            CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
            CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
            CEP.TRC(SCCGWA, BPRTHIS.AP_CODE);
            CEP.TRC(SCCGWA, BPRTHIS.TLR);
            CEP.TRC(SCCGWA, BPRTHIS.BR);
            CEP.TRC(SCCGWA, BPCTTHIB.DATE);
            CEP.TRC(SCCGWA, BPCTTHIB.VCH_NO);
            CEP.TRC(SCCGWA, BPCTTHIB.AP_CODE);
            CEP.TRC(SCCGWA, BPCTTHIB.TLR);
            CEP.TRC(SCCGWA, BPCTTHIB.BR);
        } else if (BPCTTHIB.FUNC == 'E') {
            B030_ENDBR_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "DEV2");
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTTHIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_STARTBR_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTTHIB.DATE);
        CEP.TRC(SCCGWA, BPCTTHIB.BR);
        if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE_VCH();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE_VCH();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE_AP();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE_AP();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE_TLR();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE_TLR();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE_VCH_AP();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() == 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE_VCH_AP();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE_VCH_TLR();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() == 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE_VCH_TLR();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_DATE_AP_TLR();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() == 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_DATE_AP_TLR();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() == 0) {
            T000_STARTBR_BY_ALL();
            if (pgmRtn) return;
        } else if (BPCTTHIB.VCH_NO.trim().length() > 0 
                && BPCTTHIB.AP_CODE.trim().length() > 0 
                && BPCTTHIB.TLR.trim().length() > 0 
                && BPCTTHIB.AC.trim().length() > 0) {
            T000_STARTBR1_BY_ALL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCTTHIB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_STARTBR_REC_PAY_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_REC_PAY_BPTTHIS();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_REC_PAY_PROC_CN() throws IOException,SQLException,Exception {
        T000_STARTBR_REC_PAY_THIS_CN();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTTHIS();
        if (pgmRtn) return;
    }
    public void B030_ENDBR_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTTHIS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_DATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCTTHIB.DATE);
        CEP.TRC(SCCGWA, BPCTTHIB.BR);
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_DATE_VCH() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE_VCH() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_DATE_AP() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND AP_CODE = :BPRTHIS.AP_CODE";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE_AP() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND AP_CODE = :BPRTHIS.AP_CODE "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_DATE_TLR() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND TLR = :BPRTHIS.TLR";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE_TLR() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND TLR = :BPRTHIS.TLR "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_DATE_VCH_AP() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND AP_CODE = :BPRTHIS.AP_CODE";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE_VCH_AP() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND AP_CODE = :BPRTHIS.AP_CODE "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_DATE_VCH_TLR() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND TLR = :BPRTHIS.TLR";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE_VCH_TLR() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND TLR = :BPRTHIS.TLR "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_DATE_AP_TLR() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND AP_CODE = :BPRTHIS.AP_CODE "
            + "AND TLR = :BPRTHIS.TLR";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_DATE_AP_TLR() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND AP_CODE = :BPRTHIS.AP_CODE "
            + "AND TLR = :BPRTHIS.TLR "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_BY_ALL() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND AP_CODE = :BPRTHIS.AP_CODE "
            + "AND TLR = :BPRTHIS.TLR";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR1_BY_ALL() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.where = "( 'DATE' BETWEEN :WS_DATE "
            + "AND :WS_END_DATE ) "
            + "AND BR = :BPRTHIS.BR "
            + "AND VCH_NO = :BPRTHIS.KEY.VCH_NO "
            + "AND AP_CODE = :BPRTHIS.AP_CODE "
            + "AND TLR = :BPRTHIS.TLR "
            + "AND AC = :BPRTHIS.AC";
        BPTTHIS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_REC_PAY_THIS_CN() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.set = "THIS-DC-FLG=DC_FLG,THIS-AMT=SUM(AMT)";
        BPTTHIS_BR.rp.where = "'DATE' = :BPRTHIS.KEY.DATE "
            + "AND ( RCE_PBNO = :BPRTHIS.RCE_PBNO "
            + "OR PAY_PBNO = :BPRTHIS.PAY_PBNO ) "
            + "AND TR_CODE < > :BPRTHIS.TR_CODE "
            + "AND CCY = :BPRTHIS.CCY "
            + "AND BR = :BPRTHIS.BR "
            + "AND STS = :BPRTHIS.STS";
        BPTTHIS_BR.rp.grp = "DC_FLG";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_STARTBR_REC_PAY_BPTTHIS() throws IOException,SQLException,Exception {
        BPTTHIS_BR.rp = new DBParm();
        BPTTHIS_BR.rp.TableName = "BPTTHIS";
        BPTTHIS_BR.rp.set = "THIS-DC-FLG=DC_FLG,THIS-AMT=SUM(AMT)";
        BPTTHIS_BR.rp.where = "DATE = :BPRTHIS.KEY.DATE "
            + "AND RCE_PBNO NOT LIKE :BPRTHIS.RCE_PBNO "
            + "AND PAY_PBNO NOT LIKE :BPRTHIS.PAY_PBNO "
            + "AND CCY = :BPRTHIS.CCY "
            + "AND BR = :BPRTHIS.BR "
            + "AND STS = :BPRTHIS.STS";
        BPTTHIS_BR.rp.grp = "DC_FLG";
        IBS.STARTBR(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
    }
    public void T000_READNEXT_BPTTHIS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTHIS, this, BPTTHIS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCTTHIB.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCTTHIB.RETURN_INFO = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTTHIS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTHIS_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCTTHIB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCTTHIB = ");
            CEP.TRC(SCCGWA, BPCTTHIB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
