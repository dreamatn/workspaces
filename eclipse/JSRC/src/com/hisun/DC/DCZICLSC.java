package com.hisun.DC;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZICLSC {
    DBParm DCTIACLS_RD;
    brParm DCTIACLS_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char WS_IACLS_REC_FLG = ' ';
    char WS_END_FLG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRIACLS DCRIACLS = new DCRIACLS();
    String WS_ICLSC_AC = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCICLSC DCCICLSC;
    public void MP(SCCGWA SCCGWA, DCCICLSC DCCICLSC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCICLSC = DCCICLSC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZICLSC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "DC0000", DCCICLSC.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B040_CAN_CLS_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DCCICLSC.INP_DATA.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, DCCMSG_ERROR_MSG.DC_AC_MUST_INPUT, DCCICLSC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_CAN_CLS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRIACLS);
        DCRIACLS.KEY.AC = DCCICLSC.INP_DATA.AC;
        T000_STRBR_BY_AC_PROC();
        if (pgmRtn) return;
        T000_READ_NEXT_PROC();
        if (pgmRtn) return;
        while (WS_END_FLG != 'Y') {
            R000_CAN_CLS_PROC();
            if (pgmRtn) return;
            T000_READ_NEXT_PROC();
            if (pgmRtn) return;
        }
        T000_END_BROWSE_PROC();
        if (pgmRtn) return;
    }
    public void R000_CAN_CLS_PROC() throws IOException,SQLException,Exception {
        DCRIACLS.STS = 'D';
        DCRIACLS.DEL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRIACLS.DEL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRIACLS.AUT_TLR2 = SCCGWA.COMM_AREA.SUP1_ID;
        DCRIACLS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRIACLS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DCTIACLS();
        if (pgmRtn) return;
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
    public void T000_STRBR_BY_AC_PROC() throws IOException,SQLException,Exception {
        WS_ICLSC_AC = DCCICLSC.INP_DATA.AC;
        DCTIACLS_BR.rp = new DBParm();
        DCTIACLS_BR.rp.TableName = "DCTIACLS";
        DCTIACLS_BR.rp.upd = true;
        DCTIACLS_BR.rp.where = "AC = :WS_ICLSC_AC "
            + "AND STS = 'N'";
        IBS.STARTBR(SCCGWA, DCRIACLS, this, DCTIACLS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACLS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRIACLS, this, DCTIACLS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_END_FLG = 'Y';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTIACLS";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTIACLS_BR);
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
        if (DCCICLSC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "DCCICLSC=");
            CEP.TRC(SCCGWA, DCCICLSC);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
