package com.hisun.LN;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSCINO {
    DBParm LNTSUBS_RD;
    DBParm LNTFUND_RD;
    brParm LNTSUBS_BR = new brParm();
    brParm LNTFUND_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    LNZSCINO_WS_MSGID WS_MSGID = new LNZSCINO_WS_MSGID();
    char WS_REC_SUBS_FLG = ' ';
    char WS_REC_FUND_FLG = ' ';
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNRFUND LNRFUND = new LNRFUND();
    LNCMSG_ERROR_MSG LNCMSG_ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCAWAC SCCAWAC;
    LNCSCINO LNCSCINO;
    public void MP(SCCGWA SCCGWA, LNCSCINO LNCSCINO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.LNCSCINO = LNCSCINO;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSCINO return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_MODIFY_CI_NO_LNTSUBS();
        if (pgmRtn) return;
        B020_MODIFY_CI_NO_LNTFUND();
        if (pgmRtn) return;
    }
    public void B010_MODIFY_CI_NO_LNTSUBS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.CI_NO = LNCSCINO.CI_NO;
        T000_STARTBR_LNTSUBS_CINO();
        if (pgmRtn) return;
        T000_READNEXT_LNTSUBS();
        if (pgmRtn) return;
        while (WS_REC_SUBS_FLG != 'N') {
            T000_READ_UPDATE_LNTSUBS();
            if (pgmRtn) return;
            LNRSUBS.CI_NO = LNCSCINO.CI_NO2;
            T000_REWRITE_LNTSUBS();
            if (pgmRtn) return;
            T000_READNEXT_LNTSUBS();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTSUBS();
        if (pgmRtn) return;
    }
    public void B020_MODIFY_CI_NO_LNTFUND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        LNRFUND.CI_NO = LNCSCINO.CI_NO;
        T000_STARTBR_LNTFUND_CINO();
        if (pgmRtn) return;
        T000_READNEXT_LNTFUND();
        if (pgmRtn) return;
        while (WS_REC_FUND_FLG != 'N') {
            T000_READ_UPDATE_LNTFUND();
            if (pgmRtn) return;
            LNRFUND.CI_NO = LNCSCINO.CI_NO2;
            T000_REWRITE_LNTFUND();
            if (pgmRtn) return;
            T000_READNEXT_LNTFUND();
            if (pgmRtn) return;
        }
        T000_ENDBR_LNTFUND();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_LNTSUBS() throws IOException,SQLException,Exception {
        LNRSUBS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSUBS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.REWRITE(SCCGWA, LNRSUBS, LNTSUBS_RD);
    }
    public void T000_REWRITE_LNTFUND() throws IOException,SQLException,Exception {
        LNRFUND.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRFUND.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        IBS.REWRITE(SCCGWA, LNRFUND, LNTFUND_RD);
    }
    public void T000_READ_UPDATE_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        LNTSUBS_RD.upd = true;
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_SUBS_NOTFND, LNCSCINO.RC);
            WS_REC_SUBS_FLG = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_LNTFUND() throws IOException,SQLException,Exception {
        LNTFUND_RD = new DBParm();
        LNTFUND_RD.TableName = "LNTFUND";
        LNTFUND_RD.upd = true;
        IBS.READ(SCCGWA, LNRFUND, LNTFUND_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, LNCMSG_ERROR_MSG.LN_ERR_FUND_NOT_EXIST, LNCSCINO.RC);
            WS_REC_FUND_FLG = 'N';
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_LNTSUBS_CINO() throws IOException,SQLException,Exception {
        LNTSUBS_BR.rp = new DBParm();
        LNTSUBS_BR.rp.TableName = "LNTSUBS";
        LNTSUBS_BR.rp.where = "CI_NO = :LNRSUBS.CI_NO";
        LNTSUBS_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRSUBS, this, LNTSUBS_BR);
    }
    public void T000_STARTBR_LNTFUND_CINO() throws IOException,SQLException,Exception {
        LNTFUND_BR.rp = new DBParm();
        LNTFUND_BR.rp.TableName = "LNTFUND";
        LNTFUND_BR.rp.where = "CI_NO = :LNRFUND.CI_NO";
        LNTFUND_BR.rp.order = "PROJ_NO";
        IBS.STARTBR(SCCGWA, LNRFUND, this, LNTFUND_BR);
    }
    public void T000_READNEXT_LNTSUBS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRSUBS, this, LNTSUBS_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_SUBS_FLG = 'Y';
            CEP.TRC(SCCGWA, LNRSUBS.KEY.PROJ_NO);
            CEP.TRC(SCCGWA, LNRSUBS.CI_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_SUBS_FLG = 'N';
        }
    }
    public void T000_READNEXT_LNTFUND() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, LNRFUND, this, LNTFUND_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_REC_FUND_FLG = 'Y';
            CEP.TRC(SCCGWA, LNRFUND.KEY.PROJ_NO);
            CEP.TRC(SCCGWA, LNRFUND.CI_NO);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_REC_FUND_FLG = 'N';
        }
    }
    public void T000_ENDBR_LNTSUBS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTSUBS_BR);
    }
    public void T000_ENDBR_LNTFUND() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, LNTFUND_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (LNCSCINO.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "LNCSCINO=");
            CEP.TRC(SCCGWA, LNCSCINO);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
