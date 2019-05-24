package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZIACRR {
    brParm DCTIAACR_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_ACR_NUM = 0;
    char WS_END_FLG = ' ';
    char WS_REC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIAACR DCRIAACR = new DCRIAACR();
    String WS_IACRR_VIA_AC = " ";
    String WS_IACRR_CCY = " ";
    char WS_IACRR_CCY_TYP = ' ';
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCIACRR DCCIACRR;
    public void MP(SCCGWA SCCGWA, DCCIACRR DCCIACRR) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCIACRR = DCCIACRR;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZIACRR return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCIACRR.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_RTV_DFT_AC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCIACRR.INP_DATA.VIA_AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT, DCCIACRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCIACRR.INP_DATA.CCY.equalsIgnoreCase("156") 
            && DCCIACRR.INP_DATA.CCY_TYP == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_CCY_TYP_INVALID, DCCIACRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_RTV_DFT_AC_PROC() throws IOException,SQLException,Exception {
        if (DCCIACRR.INP_DATA.CCY.trim().length() > 0 
            && DCCIACRR.INP_DATA.CCY_TYP != ' ') {
            T000_STRBR_BY_VIA_CCY_PROC();
            if (pgmRtn) return;
        } else {
            T000_STRBR_BY_VIA_PROC();
            if (pgmRtn) return;
        }
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        CEP.TRC(SCCGWA, WS_END_FLG);
        CEP.TRC(SCCGWA, WS_REC_FLG);
        CEP.TRC(SCCGWA, DCRIAACR.ACCR_FLG);
        CEP.TRC(SCCGWA, DCRIAACR.DEFAULT_FLG);
        while (WS_END_FLG != 'Y' 
            && WS_REC_FLG != 'Y') {
            if (DCRIAACR.ACCR_FLG == '1' 
                && DCRIAACR.DEFAULT_FLG == 'Y') {
                CEP.TRC(SCCGWA, "YYYYYY");
                WS_REC_FLG = 'Y';
                R000_OUTPUT_DATA();
                if (pgmRtn) return;
            }
            WS_ACR_NUM = WS_ACR_NUM + 1;
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_ACR_NUM);
        CEP.TRC(SCCGWA, WS_REC_FLG);
        if (WS_ACR_NUM == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_ACR_RCD_NOT_FND, DCCIACRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_REC_FLG != 'Y') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_ACR_DFT_AC_NOT_FND, DCCIACRR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DATA() throws IOException,SQLException,Exception {
        DCCIACRR.OUT_DATA.FRM_APP = DCRIAACR.FRM_APP;
        DCCIACRR.OUT_DATA.SEQ = DCRIAACR.KEY.SEQ;
        DCCIACRR.OUT_DATA.SUB_AC = DCRIAACR.SUB_AC;
        DCCIACRR.INP_DATA.CCY = DCRIAACR.CCY;
        DCCIACRR.INP_DATA.CCY_TYP = DCRIAACR.CCY_TYPE;
        DCCIACRR.OUT_DATA.VCH_TYPE = DCRIAACR.VCH_TYPE;
        DCCIACRR.OUT_DATA.VCH_ID = DCRIAACR.VCH_ID;
        DCCIACRR.OUT_DATA.VCH_NO = DCRIAACR.VCH_NO;
        DCCIACRR.OUT_DATA.ACCR_FLG = DCRIAACR.ACCR_FLG;
        DCCIACRR.OUT_DATA.STD_AC_FLG = DCRIAACR.STD_AC_FLG;
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        CEP.TRC(SCCGWA, DCCIACRR.OUT_DATA.SUB_AC);
        CEP.TRC(SCCGWA, DCRIAACR.CCY);
        CEP.TRC(SCCGWA, DCCIACRR.INP_DATA.CCY);
        CEP.TRC(SCCGWA, DCRIAACR.CCY_TYPE);
        CEP.TRC(SCCGWA, DCCIACRR.INP_DATA.CCY_TYP);
    }
    public void T000_STRBR_BY_VIA_CCY_PROC() throws IOException,SQLException,Exception {
        WS_IACRR_VIA_AC = DCCIACRR.INP_DATA.VIA_AC;
        WS_IACRR_CCY = DCCIACRR.INP_DATA.CCY;
        WS_IACRR_CCY_TYP = DCCIACRR.INP_DATA.CCY_TYP;
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_IACRR_VIA_AC "
            + "AND CCY = :WS_IACRR_CCY "
            + "AND CCY_TYPE = :WS_IACRR_CCY_TYP";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STRBR_BY_VIA_PROC() throws IOException,SQLException,Exception {
        WS_IACRR_VIA_AC = DCCIACRR.INP_DATA.VIA_AC;
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_IACRR_VIA_AC "
            + "AND SEQ > 0";
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
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
        if (DCCIACRR.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCIACRR=");
            CEP.TRC(SCCGWA, DCCIACRR);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
