package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSCCVA {
    DBParm DCTIAACR_RD;
    brParm DCTIAACR_BR = new brParm();
    DBParm DCTSPAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    char WS_RETURN_INFO = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIAACR DCRIAACR = new DCRIAACR();
    DCRSPAC DCRSPAC = new DCRSPAC();
    DCCICLSA DCCICLSA = new DCCICLSA();
    DCCIMSTR DCCIMSTR = new DCCIMSTR();
    DCCIMSTC DCCIMSTC = new DCCIMSTC();
    String WS_SCCVA_VIA_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSCCVA DCCSCCVA;
    public void MP(SCCGWA SCCGWA, DCCSCCVA DCCSCCVA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSCCVA = DCCSCCVA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSCCVA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_RTV_IAMST_DATA();
        if (pgmRtn) return;
        B030_CHK_IAMST_INFO();
        if (pgmRtn) return;
        B040_CHK_IAACR_INFO();
        if (pgmRtn) return;
        B070_WRT_IACLS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCSCCVA.VIA_AC.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_RTV_IAMST_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCIMSTR);
        DCCIMSTR.KEY.VIA_AC = DCCSCCVA.VIA_AC;
        S000_CALL_DCZIMSTR();
        if (pgmRtn) return;
    }
    public void B030_CHK_IAMST_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCIMSTR.DATA.PRD_TYPE);
        if (!DCCIMSTR.DATA.PRD_TYPE.equalsIgnoreCase("07")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VA_PRD_TYPE_INVALID;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCIMSTR.DATA.AC_STS == 'C') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_VIA_AC_MST_ALR_CLS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHK_IAACR_INFO1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        DCRIAACR.KEY.VIA_AC = DCCSCCVA.VIA_AC;
        DCRIAACR.ACCR_FLG = '1';
        T000_READ_DCTIAACR();
        if (pgmRtn) return;
        if (WS_RETURN_INFO != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SUB_AC_NOT_CLS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_CHK_IAACR_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIAACR);
        WS_SCCVA_VIA_AC = DCCSCCVA.VIA_AC;
        T000_STARTBR_DCTIAACR();
        if (pgmRtn) return;
        T000_READNEXT_DCTIAACR();
        if (pgmRtn) return;
        while (WS_RETURN_INFO != 'N') {
            CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
            CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
            CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
            CEP.TRC(SCCGWA, DCRIAACR.ACCR_FLG);
            if (DCRIAACR.ACCR_FLG == '1') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_SUB_AC_NOT_CLS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_DCTIAACR();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRIAACR.KEY.VIA_AC);
        CEP.TRC(SCCGWA, DCRIAACR.KEY.SEQ);
        CEP.TRC(SCCGWA, DCRIAACR.SUB_AC);
        T000_ENDBR_DCTIAACR();
        if (pgmRtn) return;
    }
    public void B040_10_UPD_IAACR_PROC() throws IOException,SQLException,Exception {
        if (DCRIAACR.ACCR_FLG == '1') {
            DCRIAACR.ACCR_FLG = '0';
            DCRIAACR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRIAACR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTIAACR();
            if (pgmRtn) return;
        }
    }
    public void B070_WRT_IACLS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCICLSA);
        DCCICLSA.INP_DATA.TYPE = "1";
        DCCICLSA.INP_DATA.AC = DCCSCCVA.VIA_AC;
        S000_CALL_DCZICLSA();
        if (pgmRtn) return;
    }
    public void S000_CALL_DCZIMSTC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-MST-CLS", DCCIMSTC);
        if (DCCIMSTC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTC.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZIMSTR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-INQ-IAMST", DCCIMSTR);
        if (DCCIMSTR.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCIMSTR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZICLSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-I-ADD-CLS", DCCICLSA);
        if (DCCICLSA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCICLSA.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.errhdl = true;
        IBS.READ(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp = new DBParm();
        DCTIAACR_BR.rp.TableName = "DCTIAACR";
        DCTIAACR_BR.rp.where = "VIA_AC = :WS_SCCVA_VIA_AC";
        DCTIAACR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
    }
    public void T000_READNEXT_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_BR.rp.errhdl = true;
        IBS.READNEXT(SCCGWA, DCRIAACR, this, DCTIAACR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RETURN_INFO = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIAACR() throws IOException,SQLException,Exception {
        DCTIAACR_RD = new DBParm();
        DCTIAACR_RD.TableName = "DCTIAACR";
        DCTIAACR_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRIAACR, DCTIAACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIAACR ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIAACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTIAACR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIAACR_BR);
    }
    public void T000_READUPD_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.upd = true;
        IBS.READ(SCCGWA, DCRSPAC, DCTSPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTSPAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTSPAC() throws IOException,SQLException,Exception {
        DCTSPAC_RD = new DBParm();
        DCTSPAC_RD.TableName = "DCTSPAC";
        DCTSPAC_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRSPAC, DCTSPAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTSPAC ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTSPAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
