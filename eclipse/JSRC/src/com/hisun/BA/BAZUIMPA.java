package com.hisun.BA;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BAZUIMPA {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_SCSSCKDT = "SCSSCKDT";
    String K_HIS_RMKS = "BILL PLEDGE";
    String WS_ERR_MSG = " ";
    BARTXDL BARTXDL = new BARTXDL();
    BARMST1 BARMST1 = new BARMST1();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCBINF SCCBINF = new SCCBINF();
    BACFTXDL BACFTXDL = new BACFTXDL();
    BACFMST1 BACFMST1 = new BACFMST1();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BACUIMPA BACUIMPA;
    public void MP(SCCGWA SCCGWA, BACUIMPA BACUIMPA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUIMPA = BACUIMPA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUIMPA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        BACUIMPA.RC.RC_MMO = "BA";
        BACUIMPA.RC.RC_CODE = 0;
        CEP.TRC(SCCGWA, BACUIMPA.DATA.BILL_NO);
        CEP.TRC(SCCGWA, BACUIMPA.DATA.FUN_COD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARMST1);
        IBS.init(SCCGWA, BACFMST1);
        BARMST1.BILL_NO = BACUIMPA.DATA.BILL_NO;
        BACFMST1.FUNC = 'T';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
        if (BACUIMPA.DATA.FUN_COD == '1' 
            && BARMST1.BILL_STS != '0') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BILL_STS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACUIMPA.DATA.FUN_COD == '1' 
            && BARMST1.BILL_MAT_DT == SCCGWA.COMM_AREA.TR_DATE) {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BILL_IS_DUE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACUIMPA.DATA.FUN_COD == '1' 
            && BARMST1.PLDG_STS == '0') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_PLDG_STS_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACUIMPA.DATA.FUN_COD == '2' 
            && BARMST1.PLDG_STS != '0') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_PLDG_STS_ERR2;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACUIMPA.DATA.FUN_COD == '1' 
            && BARMST1.BLK_STS == '0') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BLK_STS_FROZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BACUIMPA.DATA.FUN_COD == '2' 
            && BARMST1.BLK_STS == '0') {
            WS_ERR_MSG = BACMSG_ERROR_MSG.BA_BLK_STS_FROZ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        B022_UPDT_MST1_PROC();
        if (pgmRtn) return;
        B023_WRITE_TXDL_PROC();
        if (pgmRtn) return;
    }
    public void B022_UPDT_MST1_PROC() throws IOException,SQLException,Exception {
        if (BACUIMPA.DATA.FUN_COD == '1') {
            BARMST1.PLDG_STS = '0';
        }
        if (BACUIMPA.DATA.FUN_COD == '2') {
            BARMST1.PLDG_STS = '1';
        }
        BARMST1.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARMST1.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BACFMST1.FUNC = 'U';
        S000_CALL_BAZFMST1();
        if (pgmRtn) return;
    }
    public void B023_WRITE_TXDL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BARTXDL);
        IBS.init(SCCGWA, BACFTXDL);
        BARTXDL.KEY.TX_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.KEY.CRE_JRN = SCCGWA.COMM_AREA.JRN_NO;
        BARTXDL.CNTR_NO = BARMST1.KEY.CNTR_NO;
        BARTXDL.ACCT_CNT = BARMST1.KEY.ACCT_CNT;
        BARTXDL.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BARTXDL.FUN_CD = BACUIMPA.DATA.FUN_COD;
        BARTXDL.PRPH_SYS_CD = SCCGWA.COMM_AREA.REQ_SYSTEM;
        BARTXDL.PRPH_SYS_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARTXDL.PRPH_JRN = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BARTXDL.PRPH_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BARTXDL.PRPH_JRN = "0" + BARTXDL.PRPH_JRN;
        BARTXDL.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        BARTXDL.TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BARTXDL.BILL_NO = BACUIMPA.DATA.BILL_NO;
        BARTXDL.TX_AC = BARMST1.DRWR_AC;
        BARTXDL.SUSP_NO = BARMST1.SUSP_NO;
        BARTXDL.TX_CUR = BARMST1.BILL_CUR;
        BARTXDL.TX_AMT = BARMST1.BILL_AMT;
        BARTXDL.REC_FLG = '1';
        BARMST1.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARMST1.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BARMST1.UPDT_DT = SCCGWA.COMM_AREA.AC_DATE;
        BARMST1.UPDT_TLR = SCCGWA.COMM_AREA.TL_ID;
        BACFTXDL.FUNC = 'A';
        S000_CALL_BAZFTXDL();
        if (pgmRtn) return;
    }
    public void S000_CALL_BAZFMST1() throws IOException,SQLException,Exception {
        BACFMST1.REC_PTR = BARMST1;
        BACFMST1.REC_LEN = 1163;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFMST1", BACFMST1);
        if (BACFMST1.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "1111");
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFMST1.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BAZFTXDL() throws IOException,SQLException,Exception {
        BACFTXDL.REC_PTR = BARTXDL;
        BACFTXDL.REC_LEN = 514;
        IBS.CALLCPN(SCCGWA, "BA-R-BAZFTXDL", BACFTXDL);
        if (BACFTXDL.RETURN_INFO != 'F') {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BACFTXDL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_ERR_MSG_PROC_INFO() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, SCCBINF);
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
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
