package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUIOSS {
    String JIBS_tmp_str[] = new String[10];
    brParm BATLOSS_BR = new brParm();
    boolean pgmRtn = false;
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_CNTR_NO = " ";
    short WS_ACCT_CNT = 0;
    short WS_SEQ = 0;
    char WS_FOUND_FLG = ' ';
    BARLOSS BARLOSS = new BARLOSS();
    BARMST1 BARMST1 = new BARMST1();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    CICACCU CICACCU = new CICACCU();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BACFLOSS BACFLOSS = new BACFLOSS();
    BACFMST1 BACFMST1 = new BACFMST1();
    SCCGWA SCCGWA;
    BACUIOSS BACUIOSS;
    public void MP(SCCGWA SCCGWA, BACUIOSS BACUIOSS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUIOSS = BACUIOSS;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUIOSS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        BACUIOSS.RC.RC_MMO = "BA";
        BACUIOSS.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B000_DATE_DEAL_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B000_DATE_DEAL_PROC() throws IOException,SQLException,Exception {
        if (BACUIOSS.COMM_DATA.OPEN_DT == 0) {
            BACUIOSS.COMM_DATA.OPEN_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        T000_STARTBR_BATLOSS();
        if (pgmRtn) return;
        T000_READNEXT_BATLOSS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111111111BB");
        CEP.TRC(SCCGWA, BARLOSS.LOSS_DT);
        CEP.TRC(SCCGWA, "00000");
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_FOUND_FLG != 'Y') {
            if (BARLOSS.LOSS_DT <= BACUIOSS.COMM_DATA.OPEN_DT) {
                WS_FOUND_FLG = 'Y';
                CEP.TRC(SCCGWA, "11111111111111111AA");
                WS_CNTR_NO = BARLOSS.CNTR_NO;
                WS_ACCT_CNT = BARLOSS.ACCT_CNT;
                WS_SEQ = (short) (BARLOSS.SEQ + 1);
                IBS.init(SCCGWA, BARMST1);
                IBS.init(SCCGWA, BACFMST1);
                BARMST1.BILL_NO = BACUIOSS.COMM_DATA.ID_NO;
                BACFMST1.FUNC = 'T';
                S000_CALL_BAZFMST1();
                if (pgmRtn) return;
                BACUIOSS.COMM_DATA.RPT_LOSS_NM = BARLOSS.RPT_LOSS_NM;
                BACUIOSS.COMM_DATA.RPT_LOSS_ADDR = BARLOSS.RPT_LOSS_ADDR;
                BACUIOSS.COMM_DATA.RPT_LOSS_TEL = BARLOSS.RPT_LOSS_TEL;
                BACUIOSS.COMM_DATA.PBCT_NO = BARLOSS.PBCT_NO;
                BACUIOSS.COMM_DATA.STOP_ADV_NO = BARLOSS.STOP_ADV_NO;
                BACUIOSS.COMM_DATA.LOSS_ED_DT = BARLOSS.LOSS_ED_DT;
                BACUIOSS.COMM_DATA.RPT_LOSS_RSN = BARLOSS.RPT_LOSS_RSN;
                BACUIOSS.COMM_DATA.DRWR_AC = BARMST1.DRWR_AC;
                BACUIOSS.COMM_DATA.WO_PAY_FLG = BARMST1.WO_PAY_FLG;
                BACUIOSS.COMM_DATA.BILL_AMT = BARMST1.BILL_AMT;
                BACUIOSS.COMM_DATA.PAY_DT = BARMST1.PAY_DT;
                BACUIOSS.COMM_DATA.LOSS_DT = BARLOSS.LOSS_DT;
                BACUIOSS.COMM_DATA.REMK = BARLOSS.REMK;
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.RPT_LOSS_NM);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.RPT_LOSS_ADDR);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.RPT_LOSS_TEL);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.PBCT_NO);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.STOP_ADV_NO);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.LOSS_ED_DT);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.RPT_LOSS_RSN);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.DRWR_AC);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.WO_PAY_FLG);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.BILL_AMT);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.PAY_DT);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.LOSS_DT);
                CEP.TRC(SCCGWA, BACUIOSS.COMM_DATA.REMK);
            } else {
                T000_READNEXT_BATLOSS();
                if (pgmRtn) return;
            }
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_LOSS_NOTFND, BACUIOSS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BARLOSS);
            IBS.init(SCCGWA, BACFLOSS);
            BARLOSS.CNTR_NO = WS_CNTR_NO;
            BARLOSS.ACCT_CNT = WS_ACCT_CNT;
            BARLOSS.SEQ = WS_SEQ;
            BACFLOSS.FUNC = 'Z';
            S000_CALL_BAZFLOSS();
            if (pgmRtn) return;
            if (BACFLOSS.RETURN_INFO == 'N') {
            } else {
                if (BACFLOSS.RETURN_INFO == 'F') {
                    WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BILL_HAVE_UNSUP;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                    JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BACFLOSS.RC);
                    IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUIOSS.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
        T000_ENDBR_BATLOSS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BATLOSS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARLOSS);
        BARLOSS.LOSS_STS = '0';
        BARLOSS.BILL_NO = BACUIOSS.COMM_DATA.ID_NO;
        CEP.TRC(SCCGWA, BARLOSS.LOSS_STS);
        CEP.TRC(SCCGWA, BARLOSS.BILL_NO);
        BATLOSS_BR.rp = new DBParm();
        BATLOSS_BR.rp.TableName = "BATLOSS";
        BATLOSS_BR.rp.where = "BILL_NO = :BARLOSS.BILL_NO "
            + "AND LOSS_STS = :BARLOSS.LOSS_STS";
        BATLOSS_BR.rp.order = "TS DESC";
        IBS.STARTBR(SCCGWA, BARLOSS, this, BATLOSS_BR);
    }
    public void T000_READNEXT_BATLOSS() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BARLOSS, this, BATLOSS_BR);
    }
    public void T000_ENDBR_BATLOSS() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BATLOSS_BR);
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFLOSS() throws IOException,SQLException,Exception {
        BACFLOSS.REC_PTR = BARLOSS;
        BACFLOSS.REC_LEN = 898;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFLOSS", BACFLOSS);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
