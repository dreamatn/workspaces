package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZICLSA {
    int JIBS_tmp_int;
    DBParm DCTIACLS_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_IACLS_REC_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACLS DCRIACLS = new DCRIACLS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCICLSA DCCICLSA;
    public void MP(SCCGWA SCCGWA, DCCICLSA DCCICLSA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCICLSA = DCCICLSA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZICLSA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCICLSA.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B040_CRT_CLS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (!DCCICLSA.INP_DATA.TYPE.equalsIgnoreCase("1") 
            && !DCCICLSA.INP_DATA.TYPE.equalsIgnoreCase("2")) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_TYPE_INVALID, DCCICLSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (DCCICLSA.INP_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_MUST_INPUT, DCCICLSA.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_CRT_CLS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACLS);
        DCRIACLS.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACLS.KEY.TYPE = DCCICLSA.INP_DATA.TYPE.charAt(0);
        DCRIACLS.KEY.AC = DCCICLSA.INP_DATA.AC;
        T00_READ_DCTIACLS_R();
        if (pgmRtn) return;
        if (WS_IACLS_REC_FLG == 'N') {
            DCRIACLS.KEY.AC = "" + SCCGWA.COMM_AREA.AC_DATE;
            JIBS_tmp_int = DCRIACLS.KEY.AC.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) DCRIACLS.KEY.AC = "0" + DCRIACLS.KEY.AC;
            DCRIACLS.KEY.TYPE = DCCICLSA.INP_DATA.TYPE.charAt(0);
            DCRIACLS.KEY.AC = DCCICLSA.INP_DATA.AC;
            DCRIACLS.STS = 'N';
            DCRIACLS.TSK_STS = 'W';
            DCRIACLS.TRS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRIACLS.TRS_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRIACLS.AUT_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            DCRIACLS.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRIACLS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DCTIACLS();
            if (pgmRtn) return;
        } else {
            DCRIACLS.STS = 'N';
            DCRIACLS.TSK_STS = 'W';
            DCRIACLS.TRS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DCRIACLS.TRS_TLR = SCCGWA.COMM_AREA.TL_ID;
            DCRIACLS.AUT_TLR1 = SCCGWA.COMM_AREA.SUP1_ID;
            DCRIACLS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DCRIACLS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DCTIACLS();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_DCTIACLS_R() throws IOException,SQLException,Exception {
        DCTIACLS_RD = new DBParm();
        DCTIACLS_RD.TableName = "DCTIACLS";
        DCTIACLS_RD.upd = true;
        IBS.READ(SCCGWA, DCRIACLS, DCTIACLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_IACLS_REC_FLG = 'Y';
            if (DCRIACLS.STS == 'N') {
                IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CLS_RCD_ALR_EXS, DCCICLSA.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_IACLS_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "READ TABLE DCTIACLS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACLS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTIACLS() throws IOException,SQLException,Exception {
        DCTIACLS_RD = new DBParm();
        DCTIACLS_RD.TableName = "DCTIACLS";
        DCTIACLS_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRIACLS, DCTIACLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_IA_CLS_RCD_ALR_EXS, DCCICLSA.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACLS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTIACLS() throws IOException,SQLException,Exception {
        DCTIACLS_RD = new DBParm();
        DCTIACLS_RD.TableName = "DCTIACLS";
        DCTIACLS_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, DCRIACLS, DCTIACLS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "UPDATE TABLE DCTIACLS ERROR!";
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACLS";
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (DCCICLSA.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCICLSA=");
            CEP.TRC(SCCGWA, DCCICLSA);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
