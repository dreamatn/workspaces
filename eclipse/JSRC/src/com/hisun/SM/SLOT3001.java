package com.hisun.SM;

import com.hisun.BP.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.AI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class SLOT3001 {
    String JIBS_tmp_str[] = new String[10];
    brParm SLTAC_BR = new brParm();
    DBParm SLTAC_RD;
    boolean pgmRtn = false;
    String K_ITM_BOOK_FLG = "BK002";
    int WS_MAX_ROWS = 500;
    int K_MAX_ROW = 30;
    int K_MAX_COL = 250;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_ROWS_CNT = 0;
    SLOT3001_WS_ACNO WS_ACNO = new SLOT3001_WS_ACNO();
    char WS_AC_FLG = ' ';
    char WS_PROP_TYP = ' ';
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SLCOBAL SLCOBAL = new SLCOBAL();
    BPCIFHSA BPCIFHSA = new BPCIFHSA();
    AICPQITM AICPQITM = new AICPQITM();
    SLRAC SLRAC = new SLRAC();
    BPRFHISA BPRFHISA = new BPRFHISA();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SLB0001_AWA_0001 SLB0001_AWA_0001;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SLOT3001 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "SLB0001_AWA_0001>");
        SLB0001_AWA_0001 = (SLB0001_AWA_0001) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SLRAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SLB0001_AWA_0001.ITM.trim().length() == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_ITM_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.ITM_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, AICPQITM);
            AICPQITM.INPUT_DATA.BOOK_FLG = K_ITM_BOOK_FLG;
            AICPQITM.INPUT_DATA.NO = SLB0001_AWA_0001.ITM;
            S000_CALL_AIZPQITM();
            if (pgmRtn) return;
            WS_PROP_TYP = AICPQITM.OUTPUT_DATA.SL_FLG;
            if ((WS_PROP_TYP != '1' 
                && WS_PROP_TYP != 'THRU' 
                && WS_PROP_TYP != '4')) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INVALID_PTY;
                WS_FLD_NO = SLB0001_AWA_0001.ITM_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
            if (WS_PROP_TYP != '2') {
                SLB0001_AWA_0001.CI_NO = " ";
            }
        }
        if (SLB0001_AWA_0001.TXN_DT == 0) {
            WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_DT_MUST_INPUT;
            WS_FLD_NO = SLB0001_AWA_0001.TXN_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
            if (pgmRtn) return;
        }
        if (SLB0001_AWA_0001.PROP_CD == 0 
            && SLB0001_AWA_0001.CI_NO.trim().length() == 0) {
        } else {
            if (SLB0001_AWA_0001.CCY.trim().length() == 0) {
                WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_CCY_MUST_INPUT;
                WS_FLD_NO = SLB0001_AWA_0001.CCY_NO;
                S000_ERR_MSG_PROC_CONTINUE();
                if (pgmRtn) return;
            }
        }
        WS_ERR_MSG = SLCMSG_ERROR_MSG.SL_INPUT_DATA_ERR;
        S000_ERR_MSG_PROC_LAST();
        if (pgmRtn) return;
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SLB0001_AWA_0001.BR == 0) {
            SLRAC.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        } else {
            SLRAC.KEY.BR = SLB0001_AWA_0001.BR;
        }
        SLRAC.KEY.CCY = SLB0001_AWA_0001.CCY;
        SLRAC.KEY.ITM = SLB0001_AWA_0001.ITM;
        if (SLB0001_AWA_0001.CI_NO.trim().length() == 0) {
            if (SLB0001_AWA_0001.PROP_CD == 0) {
                B021_BROWSE_SLTAC();
                if (pgmRtn) return;
            } else {
                SLRAC.KEY.PROP_CD = SLB0001_AWA_0001.PROP_CD;
                B022_READ_SLTAC();
                if (pgmRtn) return;
            }
        } else {
            SLRAC.CI_NO = SLB0001_AWA_0001.CI_NO;
            B022_READ_SLTAC();
            if (pgmRtn) return;
        }
    }
    public void B021_BROWSE_SLTAC() throws IOException,SQLException,Exception {
        if (SLRAC.KEY.CCY.trim().length() == 0) {
            T000_STARTBR_SLTAC_1();
            if (pgmRtn) return;
        } else {
            T000_STARTBR_SLTAC_2();
            if (pgmRtn) return;
        }
        T000_READNEXT_SLTAC();
        if (pgmRtn) return;
        if (WS_AC_FLG == 'Y') {
            B021_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        while (WS_AC_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            if (SLRAC.CRT_DATE <= SLB0001_AWA_0001.TXN_DT) {
                B021_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
            T000_READNEXT_SLTAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_SLTAC();
        if (pgmRtn) return;
    }
    public void B021_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B021_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SLCOBAL);
        IBS.init(SCCGWA, WS_ACNO);
        WS_ACNO.WS_ACNO_BR = SLRAC.KEY.BR;
        WS_ACNO.WS_ACNO_CCY = SLRAC.KEY.CCY;
        WS_ACNO.WS_ACNO_ITM = SLRAC.KEY.ITM;
        WS_ACNO.WS_ACNO_PRP_CD = SLRAC.KEY.PROP_CD;
        CEP.TRC(SCCGWA, WS_ACNO);
        SLCOBAL.DATA.ACNO = IBS.CLS2CPY(SCCGWA, WS_ACNO);
        SLCOBAL.DATA.AC_NAME = SLRAC.AC_NAME;
        CEP.TRC(SCCGWA, SLCOBAL.DATA.ACNO);
        CEP.TRC(SCCGWA, SLCOBAL.DATA.AC_NAME);
        SLCOBAL.DATA.CRT_DT = SLRAC.CRT_DATE;
        SLCOBAL.DATA.LST_DT = SLRAC.UPDTBL_DATE;
        if (SLB0001_AWA_0001.TXN_DT == SCCGWA.COMM_AREA.TR_DATE 
            && SLRAC.UPDTBL_DATE == SCCGWA.COMM_AREA.TR_DATE) {
            SLCOBAL.DATA.BAL = SLRAC.AC_BAL;
        } else {
            B030_GET_AC_BAL();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, SLCOBAL.DATA.CRT_DT);
        CEP.TRC(SCCGWA, SLCOBAL.DATA.LST_DT);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, SLCOBAL);
        SCCMPAG.DATA_LEN = 228;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B022_READ_SLTAC() throws IOException,SQLException,Exception {
        T000_READ_SLTAC();
        if (pgmRtn) return;
        if (WS_AC_FLG == 'Y') {
            B021_01_OUT_TITLE();
            if (pgmRtn) return;
            B021_02_OUT_BRW_DATA();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, SLCMSG_ERROR_MSG.SL_AC_NOTFND);
        }
    }
    public void B030_GET_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCIFHSA);
        IBS.init(SCCGWA, BPRFHISA);
        BPCIFHSA.DATA.FUNC = '5';
        BPCIFHSA.DATA.REC_PT = BPRFHISA;
        CEP.TRC(SCCGWA, WS_ACNO);
        BPCIFHSA.DATA.AC = IBS.CLS2CPY(SCCGWA, WS_ACNO);
        BPCIFHSA.DATA.CCY = SLRAC.KEY.CCY;
        BPCIFHSA.DATA.AC_DT = SLB0001_AWA_0001.TXN_DT;
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.AC);
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.CCY);
        CEP.TRC(SCCGWA, BPCIFHSA.DATA.AC_DT);
        BPCIFHSA.DATA.REC_LEN = 144;
        S000_CALL_BPZIFHSA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SLCOBAL.DATA.BAL);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        CEP.TRC(SCCGWA, AICPQITM.RC);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZIFHSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-INQ-FHISA", BPCIFHSA);
        CEP.TRC(SCCGWA, BPCIFHSA.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC.RTNCODE);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            if (AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG == 'D') {
                SLCOBAL.DATA.BAL = BPRFHISA.CUR_BAL * -1;
            } else {
                SLCOBAL.DATA.BAL = BPRFHISA.CUR_BAL;
            }
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.OUTPUT_DATA.BAL_SIGN_FLG);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_END_OF_TABLE)) {
            SLCOBAL.DATA.BAL = SLRAC.AC_BAL;
        } else {
            CEP.ERR(SCCGWA, BPCIFHSA.RC);
        }
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_STARTBR_SLTAC_1() throws IOException,SQLException,Exception {
        SLTAC_BR.rp = new DBParm();
        SLTAC_BR.rp.TableName = "SLTAC";
        SLTAC_BR.rp.where = "BR = :SLRAC.KEY.BR "
            + "AND ITM = :SLRAC.KEY.ITM";
        SLTAC_BR.rp.order = "BR,ITM";
        IBS.STARTBR(SCCGWA, SLRAC, this, SLTAC_BR);
    }
    public void T000_STARTBR_SLTAC_2() throws IOException,SQLException,Exception {
        SLTAC_BR.rp = new DBParm();
        SLTAC_BR.rp.TableName = "SLTAC";
        SLTAC_BR.rp.where = "BR = :SLRAC.KEY.BR "
            + "AND CCY = :SLRAC.KEY.CCY "
            + "AND ITM = :SLRAC.KEY.ITM";
        SLTAC_BR.rp.order = "BR,CCY,ITM";
        IBS.STARTBR(SCCGWA, SLRAC, this, SLTAC_BR);
    }
    public void T000_READNEXT_SLTAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, SLRAC, this, SLTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AC_FLG = 'Y';
        } else {
            WS_AC_FLG = 'N';
        }
    }
    public void T000_ENDBR_SLTAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SLTAC_BR);
    }
    public void T000_READ_SLTAC() throws IOException,SQLException,Exception {
        if (SLB0001_AWA_0001.CI_NO.trim().length() == 0) {
            SLTAC_RD = new DBParm();
            SLTAC_RD.TableName = "SLTAC";
            IBS.READ(SCCGWA, SLRAC, SLTAC_RD);
        } else {
            SLTAC_RD = new DBParm();
            SLTAC_RD.TableName = "SLTAC";
            SLTAC_RD.where = "CI_NO = :SLRAC.CI_NO "
                + "AND BR = :SLRAC.KEY.BR "
                + "AND CCY = :SLRAC.KEY.CCY "
                + "AND ITM = :SLRAC.KEY.ITM";
            IBS.READ(SCCGWA, SLRAC, this, SLTAC_RD);
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AC_FLG = 'Y';
            if (SLRAC.CRT_DATE > SLB0001_AWA_0001.TXN_DT) {
                WS_AC_FLG = 'N';
            }
        } else {
            WS_AC_FLG = 'N';
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
        pgmRtn = true;
        return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
