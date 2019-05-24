package com.hisun.SM;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class SLZUCIF {
    brParm SLTAC_BR = new brParm();
    boolean pgmRtn = false;
    char K_PRP_TYP_2 = '2';
    String CPN_DRCR_BAL = "SL-DRCR-BAL         ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    SLZUCIF_WS_ACNO WS_ACNO = new SLZUCIF_WS_ACNO();
    double WS_TMP_BAL = 0;
    char WS_AC_FLG = ' ';
    SLCMSG_ERROR_MSG SLCMSG_ERROR_MSG = new SLCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SLCDRCR SLCDRCR = new SLCDRCR();
    SLRAC SLRAC = new SLRAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SLCUCIF SLCUCIF;
    public void MP(SCCGWA SCCGWA, SLCUCIF SLCUCIF) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SLCUCIF = SLCUCIF;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SLZUCIF return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SLRAC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        SLRAC.CI_NO = SLCUCIF.OLD_CIF;
        T000_STARTBR_SLTAC();
        if (pgmRtn) return;
        T000_READNEXT_SLTAC();
        if (pgmRtn) return;
        while (WS_AC_FLG != 'N') {
            B020_DEBIT_OLD_CIF();
            if (pgmRtn) return;
            B030_CREDIT_PRIM_CIF();
            if (pgmRtn) return;
            T000_READNEXT_SLTAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_SLTAC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (SLCUCIF.PRIM_CIF.trim().length() == 0 
            || SLCUCIF.OLD_CIF.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, SLCMSG_ERROR_MSG.SL_CINO_MUST_INPUT, SLCDRCR.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_DEBIT_OLD_CIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SLCDRCR);
        IBS.init(SCCGWA, WS_ACNO);
        WS_TMP_BAL = 0;
        WS_ACNO.WS_ACNO_BR = SLRAC.KEY.BR;
        WS_ACNO.WS_ACNO_CCY = SLRAC.KEY.CCY;
        WS_ACNO.WS_ACNO_ITM = SLRAC.KEY.ITM;
        WS_ACNO.WS_ACNO_PCD = SLRAC.KEY.PROP_CD;
        SLCDRCR.AC = IBS.CLS2CPY(SCCGWA, WS_ACNO);
        SLCDRCR.CI_NO = SLRAC.CI_NO;
        CEP.TRC(SCCGWA, SLCDRCR.AC);
        CEP.TRC(SCCGWA, SLCDRCR.CI_NO);
        SLCDRCR.PRP_TYP = K_PRP_TYP_2;
        SLCDRCR.DRCRFLG = 'D';
        WS_TMP_BAL = SLRAC.AC_BAL;
        SLCDRCR.AMT = SLRAC.AC_BAL;
        S000_CALL_SLZDRCR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SLCDRCR.RC);
    }
    public void B030_CREDIT_PRIM_CIF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SLCDRCR);
        SLCDRCR.AC = IBS.CLS2CPY(SCCGWA, WS_ACNO);
        SLCDRCR.CI_NO = SLCUCIF.PRIM_CIF;
        SLCDRCR.PRP_TYP = K_PRP_TYP_2;
        SLCDRCR.DRCRFLG = 'C';
        SLCDRCR.AMT = WS_TMP_BAL;
        S000_CALL_SLZDRCR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SLCDRCR.RC);
    }
    public void S000_CALL_SLZDRCR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_DRCR_BAL, SLCDRCR);
    }
    public void T000_STARTBR_SLTAC() throws IOException,SQLException,Exception {
        SLTAC_BR.rp = new DBParm();
        SLTAC_BR.rp.TableName = "SLTAC";
        SLTAC_BR.rp.where = "CI_NO = :SLRAC.CI_NO";
        IBS.STARTBR(SCCGWA, SLRAC, this, SLTAC_BR);
    }
    public void T000_READNEXT_SLTAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, SLRAC, this, SLTAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_AC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_AC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "SLTAC SEL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_SLTAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, SLTAC_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SLCUCIF.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SLCUCIF=");
            CEP.TRC(SCCGWA, SLCUCIF);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
