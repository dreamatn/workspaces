package com.hisun.BA;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPRPARM;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;

public class BAZUPROD {
    boolean pgmRtn = false;
    String K_FMT_ID = "BPRPARM";
    String K_HIS_RMKS = "PROD MAINTAIN";
    int K_BR = 999999;
    String K_CODE = "CLBA";
    BAZUPROD_WS_PRM_KEY WS_PRM_KEY = new BAZUPROD_WS_PRM_KEY();
    BACMSG_ERROR_MSG BACMSG_ERROR_MSG = new BACMSG_ERROR_MSG();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BARPROD BARPROD = new BARPROD();
    BPRPARM BPROPARM = new BPRPARM();
    BPRPARM BPRNPARM = new BPRPARM();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    BACUPROD BACUPROD;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA, BACUPROD BACUPROD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BACUPROD = BACUPROD;
        CEP.TRC(SCCGWA);
        CEP.TRC(SCCGWA, BACUPROD);
        A00_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BAZUPROD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A00_INIT_PROCESS() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BARPROD);
        BPCPRMM.RC.RC_APP = "BP";
        BPCPRMM.DAT_PTR = BPRPRMT;
        BACUPROD.RC.RC_APP = "BA";
        BACUPROD.RC.RC_RTNCODE = 0;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        if (BACUPROD.FUNC == 'I') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
        } else if (BACUPROD.FUNC == 'A') {
            B100_WRITE_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_TO_BPRNPARM();
            if (pgmRtn) return;
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (BACUPROD.FUNC == 'M') {
            B100_READU_PROCESS();
            if (pgmRtn) return;
            B100_REWRITE_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_TO_BPRNPARM();
            if (pgmRtn) return;
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else if (BACUPROD.FUNC == 'D') {
            B100_READ_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_TO_BPROPARM();
            if (pgmRtn) return;
            B100_DELETE_PROCESS();
            if (pgmRtn) return;
            R000_WRITE_HIS_PROC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LAST CHECK REJECT.");
            CEP.TRC(SCCGWA, BACUPROD.FUNC);
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_MUST_INPUT, BACUPROD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B200_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BACUPROD.KEY.TYPE);
        CEP.TRC(SCCGWA, BACUPROD.KEY.CODE);
        CEP.TRC(SCCGWA, BACUPROD.FUNC);
        CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.BILL_FLG);
        CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.AGTIB_FLG);
        CEP.TRC(SCCGWA, BACUPROD.EFF_DATE);
        if ((BACUPROD.FUNC != 'A' 
            && BACUPROD.FUNC != 'D' 
            && BACUPROD.FUNC != 'M' 
            && BACUPROD.FUNC != 'I')) {
            CEP.TRC(SCCGWA, "FUNC VALID CHECK REJECT.");
            IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_FUNC_INPUT, BACUPROD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BACUPROD.FUNC == 'A' 
            || BACUPROD.FUNC == 'M') {
            if (!BACUPROD.DATA_TXT.PRODMO.equalsIgnoreCase("CLBA")) {
                CEP.TRC(SCCGWA, "UPROD-PRODMO VALID CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_PRODMO_INPUT, BACUPROD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.BILL_FLG);
            if ((BACUPROD.DATA_TXT.BILL_FLG != 'E' 
                && BACUPROD.DATA_TXT.BILL_FLG != 'P')) {
                CEP.TRC(SCCGWA, "PROD MOD VALID CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_BILL_FLG_INPUT, BACUPROD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if ((BACUPROD.DATA_TXT.AGTIB_FLG != 'Y' 
                && BACUPROD.DATA_TXT.AGTIB_FLG != 'N')) {
                CEP.TRC(SCCGWA, "PROD ATTR VALID CHECK REJECT.");
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_AGTIB_FLG_INPUT, BACUPROD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BACUPROD.FUNC == 'M') {
            if (BACUPROD.EXP_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BACMSG_ERROR_MSG.BA_ERR_CODE_UNAVAIL, BACUPROD.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '3';
        BPRPRMT.KEY.TYP = BACUPROD.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = BACUPROD.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        CEP.TRC(SCCGWA, "111STA");
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "111END");
    }
    public void B100_READU_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '4';
        BPRPRMT.KEY.TYP = BACUPROD.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = BACUPROD.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_REWRITE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BACUPROD.EFF_DATE);
        BPCPRMM.FUNC = '2';
        BPRPRMT.KEY.TYP = BACUPROD.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = BACUPROD.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BPCPRMM.EFF_DT = BACUPROD.EFF_DATE;
        BPCPRMM.EXP_DT = BACUPROD.EXP_DATE;
        BPRPRMT.DESC = BACUPROD.DESC;
        BPRPRMT.CDESC = BACUPROD.CDESC;
        IBS.init(SCCGWA, BARPROD.DATA_TXT);
        BARPROD.DATA_TXT.PRODMO = BACUPROD.DATA_TXT.PRODMO;
        BARPROD.DATA_TXT.BILL_FLG = BACUPROD.DATA_TXT.BILL_FLG;
        BARPROD.DATA_TXT.AGTIB_FLG = BACUPROD.DATA_TXT.AGTIB_FLG;
        BARPROD.DATA_TXT.DELY_CODE = BACUPROD.DATA_TXT.DLY_CODE;
        BARPROD.DATA_TXT.INFO_RMD_DAYS = BACUPROD.DATA_TXT.INFO_DAY;
        CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.DLY_CODE);
        CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.OVD_RAT);
        BARPROD.DATA_TXT.OVD_RAT = BACUPROD.DATA_TXT.OVD_RAT;
        CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.DLY_CODE);
        CEP.TRC(SCCGWA, BACUPROD.DATA_TXT.OVD_RAT);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARPROD.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_DELETE_PROCESS() throws IOException,SQLException,Exception {
        BPCPRMM.FUNC = '1';
        BPRPRMT.KEY.TYP = BACUPROD.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = BACUPROD.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
    }
    public void B100_WRITE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BACUPROD.EFF_DATE);
        BPCPRMM.FUNC = '0';
        CEP.TRC(SCCGWA, BACUPROD.KEY.TYPE);
        CEP.TRC(SCCGWA, BACUPROD.KEY.CODE);
        BPRPRMT.KEY.TYP = BACUPROD.KEY.TYPE;
        WS_PRM_KEY.WS_ACBR = K_BR;
        WS_PRM_KEY.WS_PRM_CD = BACUPROD.KEY.CODE;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PRM_KEY);
        BPCPRMM.EFF_DT = BACUPROD.EFF_DATE;
        BPCPRMM.EXP_DT = BACUPROD.EXP_DATE;
        BPRPRMT.DESC = BACUPROD.DESC;
        BPRPRMT.CDESC = BACUPROD.CDESC;
        IBS.init(SCCGWA, BARPROD.DATA_TXT);
        BARPROD.DATA_TXT.PRODMO = BACUPROD.DATA_TXT.PRODMO;
        BARPROD.DATA_TXT.BILL_FLG = BACUPROD.DATA_TXT.BILL_FLG;
        BARPROD.DATA_TXT.AGTIB_FLG = BACUPROD.DATA_TXT.AGTIB_FLG;
        BARPROD.DATA_TXT.DELY_CODE = BACUPROD.DATA_TXT.DLY_CODE;
        BARPROD.DATA_TXT.INFO_RMD_DAYS = BACUPROD.DATA_TXT.INFO_DAY;
        BARPROD.DATA_TXT.OVD_RAT = BACUPROD.DATA_TXT.OVD_RAT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BARPROD.DATA_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.PRODMO);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.BILL_FLG);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.AGTIB_FLG);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.DELY_CODE);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.INFO_RMD_DAYS);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BACUPROD.EFF_DATE);
    }
    public void B200_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.CD);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.PRODMO);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.BILL_FLG);
        CEP.TRC(SCCGWA, BARPROD.DATA_TXT.AGTIB_FLG);
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
        BACUPROD.KEY.TYPE = BPRPRMT.KEY.TYP;
        BACUPROD.KEY.CODE = BPRPRMT.KEY.CD;
        BACUPROD.DESC = BPRPRMT.DESC;
        BACUPROD.CDESC = BPRPRMT.CDESC;
        BACUPROD.EFF_DATE = BPCPRMM.EFF_DT;
        BACUPROD.EXP_DATE = BPCPRMM.EXP_DT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BACUPROD.DATA_TXT);
    }
    public void R000_TRANS_DATA_TO_BPRNPARM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNPARM);
        BPRNPARM.KEY.TYPE = BPRPRMT.KEY.TYP;
        BPRNPARM.KEY.CODE = BPRPRMT.KEY.CD;
        BPRNPARM.EFF_DATE = BPCPRMM.EFF_DT;
        BPRNPARM.EXP_DATE = BPCPRMM.EXP_DT;
        BPRNPARM.DESC = BPRPRMT.DESC;
        BPRNPARM.CDESC = BPRPRMT.CDESC;
