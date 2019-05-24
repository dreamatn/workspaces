package com.hisun.LN;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.DD.*;
import com.hisun.IB.*;
import com.hisun.AI.*;
import com.hisun.GD.*;
import com.hisun.CI.*;
import com.hisun.DC.*;

import java.io.IOException;
import java.sql.SQLException;

public class LNZSRPB {
    int JIBS_tmp_int;
    DBParm LNTBALZ_RD;
    String JIBS_tmp_str[] = new String[10];
    String JIBS_NumStr;
    DBParm LNTRCVD_RD;
    BPREWA15_EVENTS EVENTS;
    DBParm LNTRELA_RD;
    DBParm LNTICTL_RD;
    DBParm LNTAPRD_RD;
    DBParm LNTSUBS_RD;
    DBParm LNTCTPY_RD;
    DBParm LNTPACK_RD;
    DBParm LNTPLDT_RD;
    DBParm LNTAGRE_RD;
    DBParm LNTTRAN_RD;
    DBParm LNTCONT_RD;
    DBParm LNTLOAN_RD;
    DBParm LNTSETL_RD;
    boolean pgmRtn = false;
    String SCSSCLDT = "SCSSCLDT";
    String DD_AC = "01";
    String INTERNAL = "02";
    String IB_AC = "03";
    String DC_AC = "05";
    String EB_AC = "06";
    LNZSRPB_WS_VARIABLES WS_VARIABLES = new LNZSRPB_WS_VARIABLES();
    LNZSRPB_WS_COND_FLG WS_COND_FLG = new LNZSRPB_WS_COND_FLG();
    LNRSETL LNRSETL = new LNRSETL();
    LNCRSETL LNCRSETL = new LNCRSETL();
    LNRRELA LNRRELA = new LNRRELA();
    LNRSUBS LNRSUBS = new LNRSUBS();
    LNCMSG_ERROR_MSG ERROR_MSG = new LNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCGCFEE BPCGCFEE = new BPCGCFEE();
    LNCGCFEE LNCGCFEE = new LNCGCFEE();
    LNCURPN LNCURPN = new LNCURPN();
    LNCOD10 LNCOD10 = new LNCOD10();
    LNCICRCM LNCICRCM = new LNCICRCM();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCUABOX BPCUABOX = new BPCUABOX();
    AICUUPIA AICUUPIA = new AICUUPIA();
    LNCSRPC LNCSRPC = new LNCSRPC();
    LNRCMMT LNRCMMT = new LNRCMMT();
    LNCRCMMT LNCRCMMT = new LNCRCMMT();
    LNRFUND LNRFUND = new LNRFUND();
    LNCRFUND LNCRFUND = new LNCRFUND();
    LNRICTL LNRICTL = new LNRICTL();
    LNRCTPY LNRCTPY = new LNRCTPY();
    GDCIQLDR GDCIQLDR = new GDCIQLDR();
    GDCUMPLD GDCUMPLD = new GDCUMPLD();
    GDCUMPDR GDCUMPDR = new GDCUMPDR();
    CICQACRI CICQACRI = new CICQACRI();
    LNRAGRE LNRAGRE = new LNRAGRE();
    LNRPLDT LNRPLDT = new LNRPLDT();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCOFSVR BPCOFSVR = new BPCOFSVR();
    CICQACAC CICQACAC = new CICQACAC();
    LNCCNEX LNCCNEX = new LNCCNEX();
    AICPQIA AICPQIA = new AICPQIA();
    LNCCLNQ LNCCLNQ = new LNCCLNQ();
    AICPQITM AICPQITM = new AICPQITM();
    AICPQMIB AICPQMIB = new AICPQMIB();
    DDCSQBAL DDCSQBAL = new DDCSQBAL();
    CICACCU CICACCU = new CICACCU();
    LNCIGEAI LNCIGEAI = new LNCIGEAI();
    LNCSSUNM LNCSSUNM = new LNCSSUNM();
    LNCPCFTA LNCPCFTA = new LNCPCFTA();
    LNCRICTL LNCRICTL = new LNCRICTL();
    LNRRCVD LNRRCVD = new LNRRCVD();
    SCCCLDT SCCCLDT = new SCCCLDT();
    LNCSPDQ LNCSPDQ = new LNCSPDQ();
    IBCQINF IBCQINF = new IBCQINF();
    LNRPACK LNRPACK = new LNRPACK();
    LNCICTLM LNCICTLM = new LNCICTLM();
    LNCSCLR LNCSCLR = new LNCSCLR();
    LNCTRANM LNCTRANM = new LNCTRANM();
    LNCSCLRR LNCSCLRR = new LNCSCLRR();
    LNRAPRD LNRAPRD = new LNRAPRD();
    CICCUST CICCUST = new CICCUST();
    SCCTPCL SCCTPCL = new SCCTPCL();
    LNCSRPO LNCSRPO = new LNCSRPO();
    LNCSRPOR LNCSRPOR = new LNCSRPOR();
    LNCAPRDM LNCAPRDM = new LNCAPRDM();
    LNRTRAN LNRTRAN = new LNRTRAN();
    LNRCONT LNRCONT = new LNRCONT();
    LNRLOAN LNRLOAN = new LNRLOAN();
    GDCSDDDR GDCSDDDR = new GDCSDDDR();
    DCCSIALP DCCSIALP = new DCCSIALP();
    GDCSTRAC GDCSTRAC = new GDCSTRAC();
    LNRPARS LNRPARS = new LNRPARS();
    GDCSRLSR GDCSRLSR = new GDCSRLSR();
    DDCUOBAL DDCUOBAL = new DDCUOBAL();
    LNCIPAMT LNCIPAMT = new LNCIPAMT();
    LNRBALZ LNRBALZ = new LNRBALZ();
    SCCGWA SCCGWA;
    SCCGAPV SCCGAPV;
    SCCBATH SCCBATH;
    BPREWA BPREWA;
    SCCGSCA_SC_AREA SC_AREA;
    SCCGBPA_BP_AREA BP_AREA;
    LNB4500_AWA_4500 AWA_4500;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "LNZSRPB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") 
            || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) {
            SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
            CEP.TRC(SCCGWA, SCCBATH.PROC_NAME);
            CEP.TRC(SCCGWA, SCCBATH.PGM_NAME);
        }
        AWA_4500 = new LNB4500_AWA_4500();
        IBS.init(SCCGWA, AWA_4500);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWA_AREA_PTR, AWA_4500);
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPREWA = (BPREWA) BP_AREA.EWA_AREA.EWA_AREA_PTR;
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_COND_FLG);
        IBS.init(SCCGWA, LNCGCFEE);
        WS_VARIABLES.WS_ERR_MSG.ERR_MSG_APP = "LN";
        WS_VARIABLES.WS_ERR_MSG.ERR_MSG_RTNCODE = 0;
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B021_MOVE_LNB4600();
            if (pgmRtn) return;
        } else {
            B021_MOVE_LNB4500();
            if (pgmRtn) return;
        }
    }
    public void B021_MOVE_LNB4600() throws IOException,SQLException,Exception {
        LNCSRPOR.RC.RC_APP = AWA_4500.RC_APP;
        LNCSRPOR.RC.RC_RTNCODE = AWA_4500.RC_CODE;
        LNCSRPOR.COMM_DATA.CTA_NO = AWA_4500.CTA_NO;
        LNCSRPOR.COMM_DATA.BR = AWA_4500.BR;
        LNCSRPOR.COMM_DATA.CI_NO = AWA_4500.CI_NO;
        LNCSRPOR.COMM_DATA.CI_CNM = AWA_4500.CI_CNM;
        LNCSRPOR.COMM_DATA.PROD_CD = AWA_4500.PROD_CD;
        LNCSRPOR.COMM_DATA.CCY = AWA_4500.CCY;
        LNCSRPOR.COMM_DATA.PRINCIPAL = AWA_4500.BAL;
        LNCSRPOR.COMM_DATA.BALANCE = AWA_4500.BALANCE;
        LNCSRPOR.COMM_DATA.TR_VAL_DTE = AWA_4500.TR_DATE;
        LNCSRPOR.COMM_DATA.APT_REF = AWA_4500.APT_REF;
        LNCSRPOR.COMM_DATA.TOT_AMT = AWA_4500.TOT_AMT;
        LNCSRPOR.COMM_DATA.TOT_P_AMT = AWA_4500.P_AMT;
        LNCSRPOR.COMM_DATA.TOT_I_AMT = AWA_4500.I_AMT;
        LNCSRPOR.COMM_DATA.TOT_O_AMT = AWA_4500.O_AMT;
        LNCSRPOR.COMM_DATA.TOT_L_AMT = AWA_4500.L_AMT;
        LNCSRPOR.COMM_DATA.MMO = AWA_4500.MMO;
        LNCSRPOR.COMM_DATA.HRG_AMT = AWA_4500.HRG_AMT;
        LNCSRPOR.COMM_DATA.RDI_FLG = AWA_4500.RDI_FLG;
        LNCSRPOR.COMM_DATA.RDI_AMT = AWA_4500.RDI_AMT;
        LNCSRPOR.COMM_DATA.ADJ_TYP = AWA_4500.ADJ_TYP;
        LNCSRPOR.COMM_DATA.ADJ_AC = AWA_4500.ADJ_AC;
        LNCSRPOR.COMM_DATA.MLT_STL = AWA_4500.MLT_STL;
        LNCSRPOR.COMM_DATA.CUR_TRM = AWA_4500.CUR_TRM;
        LNCSRPOR.COMM_DATA.CLN_CUT = AWA_4500.CLN_CUT;
        LNCSRPOR.COMM_DATA.SUBS_FLG = AWA_4500.SUBS_FLG;
        LNCSRPOR.TXN_INFO.TXN_DT = BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        LNCSRPOR.TXN_INFO.JRNNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        for (WS_VARIABLES.J = 1; WS_VARIABLES.J < 5; WS_VARIABLES.J += 1) {
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].AC_FLG2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].AC_FLG;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].STL_MTH2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].STL_MTH;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].REC_AC2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].REC_AC;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].CHQ_NO2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].CHQ_NO;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].PAY_PSWD = AWA_4500.ACAMT[WS_VARIABLES.J-1].PAY_PSWD;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].CHQ_TYPE2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].CHQ_TYPE;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].ISS_DATE2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].ISS_DATE;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].RVS_NO2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].RVS_NO;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].PAY_AMT2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].PAY_AMT;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].AMT_FRM2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].AMT_FRM;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].EVENT_CD = AWA_4500.ACAMT[WS_VARIABLES.J-1].EVENT_CD;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].PROD_CDO = AWA_4500.ACAMT[WS_VARIABLES.J-1].PROD_CDO;
            LNCSRPOR.COMM_DATA.ACAMT[WS_VARIABLES.J-1].CCY_TYP = AWA_4500.CCY_TYPE.charAt(0);
        }
        for (WS_VARIABLES.L = 1; WS_VARIABLES.L < 10; WS_VARIABLES.L += 1) {
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].SUB_C_NO = AWA_4500.PART_DAT[WS_VARIABLES.L-1].SUB_C_NO;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].BBR = AWA_4500.PART_DAT[WS_VARIABLES.L-1].BBR;
            if (AWA_4500.PART_DAT[WS_VARIABLES.L-1].SEQ_NO.trim().length() == 0) LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].SEQ_NO = 0;
            else LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].SEQ_NO = Integer.parseInt(AWA_4500.PART_DAT[WS_VARIABLES.L-1].SEQ_NO);
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].LCL_FLG = AWA_4500.PART_DAT[WS_VARIABLES.L-1].LCL_FLG;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].REL_TYP = AWA_4500.PART_DAT[WS_VARIABLES.L-1].REL_TYP;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_P_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYP_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_I_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYI_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_O_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYO_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_L_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYL_AMT;
            LNCSRPOR.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_F_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYF_AMT;
        }
    }
    public void B021_MOVE_LNB4500() throws IOException,SQLException,Exception {
        LNCSRPO.RC.RC_APP = AWA_4500.RC_APP;
        LNCSRPO.RC.RC_RTNCODE = AWA_4500.RC_CODE;
        LNCSRPO.COMM_DATA.CTA_NO = AWA_4500.CTA_NO;
        LNCSRPO.COMM_DATA.BR = AWA_4500.BR;
        LNCSRPO.COMM_DATA.CI_NO = AWA_4500.CI_NO;
        LNCSRPO.COMM_DATA.CI_CNM = AWA_4500.CI_CNM;
        LNCSRPO.COMM_DATA.PROD_CD = AWA_4500.PROD_CD;
        LNCSRPO.COMM_DATA.CCY = AWA_4500.CCY;
        LNCSRPO.COMM_DATA.PRINCIPAL = AWA_4500.BAL;
        LNCSRPO.COMM_DATA.BALANCE = AWA_4500.BALANCE;
        LNCSRPO.COMM_DATA.TR_VAL_DTE = AWA_4500.TR_DATE;
        LNCSRPO.COMM_DATA.APT_REF = AWA_4500.APT_REF;
        LNCSRPO.COMM_DATA.TOT_AMT = AWA_4500.TOT_AMT;
        LNCSRPO.COMM_DATA.TOT_P_AMT = AWA_4500.P_AMT;
        LNCSRPO.COMM_DATA.TOT_I_AMT = AWA_4500.I_AMT;
        LNCSRPO.COMM_DATA.TOT_O_AMT = AWA_4500.O_AMT;
        LNCSRPO.COMM_DATA.TOT_L_AMT = AWA_4500.L_AMT;
        LNCSRPO.COMM_DATA.MMO = AWA_4500.MMO;
        LNCSRPO.COMM_DATA.HRG_AMT = AWA_4500.HRG_AMT;
        LNCSRPO.COMM_DATA.RDI_FLG = AWA_4500.RDI_FLG;
        LNCSRPO.COMM_DATA.RDI_AMT = AWA_4500.RDI_AMT;
        LNCSRPO.COMM_DATA.ADJ_TYP = AWA_4500.ADJ_TYP;
        LNCSRPO.COMM_DATA.ADJ_AC = AWA_4500.ADJ_AC;
        LNCSRPO.COMM_DATA.MLT_STL = AWA_4500.MLT_STL;
        LNCSRPO.COMM_DATA.CUR_TRM = AWA_4500.CUR_TRM;
        LNCSRPO.COMM_DATA.CLN_CUT = AWA_4500.CLN_CUT;
        LNCSRPO.COMM_DATA.SUBS_FLG = AWA_4500.SUBS_FLG;
        for (WS_VARIABLES.J = 1; WS_VARIABLES.J < 5; WS_VARIABLES.J += 1) {
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].AC_FLG2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].AC_FLG;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].STL_MTH2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].STL_MTH;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].REC_AC2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].REC_AC;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].CHQ_NO2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].CHQ_NO;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].PAY_PSWD = AWA_4500.ACAMT[WS_VARIABLES.J-1].PAY_PSWD;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].CHQ_TYPE2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].CHQ_TYPE;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].ISS_DATE2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].ISS_DATE;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].RVS_NO2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].RVS_NO;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].PAY_AMT2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].PAY_AMT;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].AMT_FRM2 = AWA_4500.ACAMT[WS_VARIABLES.J-1].AMT_FRM;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].EVENT_CD = AWA_4500.ACAMT[WS_VARIABLES.J-1].EVENT_CD;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].PROD_CDO = AWA_4500.ACAMT[WS_VARIABLES.J-1].PROD_CDO;
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.J-1].CCY_TYP = AWA_4500.ACAMT[WS_VARIABLES.J-1].CCY_TYP;
        }
        for (WS_VARIABLES.L = 1; WS_VARIABLES.L < 10; WS_VARIABLES.L += 1) {
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].SUB_C_NO = AWA_4500.PART_DAT[WS_VARIABLES.L-1].SUB_C_NO;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].BBR = AWA_4500.PART_DAT[WS_VARIABLES.L-1].BBR;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].SEQ_NO = AWA_4500.PART_DAT[WS_VARIABLES.L-1].SEQ_NO;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].LCL_FLG = AWA_4500.PART_DAT[WS_VARIABLES.L-1].LCL_FLG;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].REL_TYP = AWA_4500.PART_DAT[WS_VARIABLES.L-1].REL_TYP;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_P_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYP_AMT;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_I_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYI_AMT;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_O_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYO_AMT;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_L_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYL_AMT;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].PY_F_AMT = AWA_4500.PART_DAT[WS_VARIABLES.L-1].PYF_AMT;
        }
        LNCSRPO.COMM_DATA.CCY_TYPE = AWA_4500.CCY_TYPE;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            B000_INPUT_VAILIDATION();
            if (pgmRtn) return;
            B000_MAIN_PROCESS();
            if (pgmRtn) return;
            B101_JY_CDD();
            if (pgmRtn) return;
            C000_CALL_DDZUOBAL();
            if (pgmRtn) return;
        } else {
            S000_CALL_LNZSRPOR();
            if (pgmRtn) return;
        }
    }
    public void B000_INPUT_VAILIDATION() throws IOException,SQLException,Exception {
        B0000_CHECK_SOMETHING();
        if (pgmRtn) return;
        B0010_CHECK_SOMETHING();
        if (pgmRtn) return;
        B010_INPUT_CHECK();
        if (pgmRtn) return;
        B020_GET_CTA_AND_PARM_INFO();
        if (pgmRtn) return;
        B030_INFO_CHECK();
        if (pgmRtn) return;
        B040_AC_CHECK();
        if (pgmRtn) return;
    }
    public void B0000_CHECK_SOMETHING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCONT);
        LNRCONT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTCONT2();
        if (pgmRtn) return;
        WS_VARIABLES.START_DATE = LNRCONT.START_DATE;
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE == WS_VARIABLES.START_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_DT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B0010_CHECK_SOMETHING() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRLOAN);
        LNRLOAN.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTLOAN();
        if (pgmRtn) return;
        WS_VARIABLES.START_DATE2 = LNRLOAN.CRT_DATE;
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE == WS_VARIABLES.START_DATE2) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_DT, LNCSRPO.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        B050_BATCH_AMT_DEBIT_PROC();
        if (pgmRtn) return;
        if (LNCCLNQ.DATA.PD_PROJ_NO != 0) {
            B052_FUND_LOAN_PROC();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(19 - 1, 19 + 1 - 1).equalsIgnoreCase("1")) {
            B051_AMT_PROC();
            if (pgmRtn) return;
        }
        B060_SOLD_OUT_LOAN_PROC();
        if (pgmRtn) return;
        B070_CUST_REPAY_PROC();
        if (pgmRtn) return;
        if (WS_COND_FLG.UPD_ICTL_FLG == 'Y') {
            B090_UPD_STSW_PROCESS();
            if (pgmRtn) return;
        }
        B110_BP_HISTORY_GEN();
        if (pgmRtn) return;
        B130_UPDATE_WAIWEI_INFO();
        if (pgmRtn) return;
    }
    public void B010_INPUT_CHECK() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.CTA_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FWTXN_REJECT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_CTA_AND_PARM_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCCLNQ);
        LNCCLNQ.DATA.CONT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCCLNQ.DATA.SUB_CONT_NO = "" + 0;
        JIBS_tmp_int = LNCCLNQ.DATA.SUB_CONT_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCCLNQ.DATA.SUB_CONT_NO = "0" + LNCCLNQ.DATA.SUB_CONT_NO;
        LNCCLNQ.FUNC = '3';
        S000_CALL_LNZICLNQ();
        if (pgmRtn) return;
        LNCSRPO.COMM_DATA.BR = LNCCLNQ.DATA.DOMI_BR;
        LNCSRPO.COMM_DATA.CI_NO = LNCCLNQ.DATA.CI_NO;
        LNCSRPO.COMM_DATA.CI_CNM = LNCCLNQ.DATA.CI_CNAME;
        LNCSRPO.COMM_DATA.PROD_CD = LNCCLNQ.DATA.PROD_CD;
        LNCSRPO.COMM_DATA.CCY = LNCCLNQ.DATA.CCY;
        LNCSRPO.COMM_DATA.BALANCE = LNCCLNQ.DATA.TOT_BAL;
        LNCSRPO.COMM_DATA.PRINCIPAL = LNCCLNQ.DATA.PRIN;
        WS_VARIABLES.CTL_STSW = LNCCLNQ.DATA.CTL_STSW;
        WS_VARIABLES.FATHER_CONTRACT = LNCCLNQ.DATA.FATHER_CONTRACT;
        IBS.init(SCCGWA, LNCAPRDM);
        LNCAPRDM.FUNC = '3';
        LNCAPRDM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        S000_CALL_LNZAPRDM();
        if (pgmRtn) return;
        if (LNCAPRDM.REC_DATA.DUE_AUTO_FLG == ' ' 
            || LNCAPRDM.REC_DATA.DUE_AUTO_FLG == '1') {
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_LOAN_CLOSE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(51 - 1, 51 + 1 - 1).equalsIgnoreCase("1") 
            || WS_VARIABLES.CTL_STSW.substring(53 - 1, 53 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NOT_SUP_DIST, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_INFO_CHECK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'A';
        CICQACAC.DATA.ACAC_NO = LNCSRPO.COMM_DATA.CTA_NO;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.TR_VAL_DTE < LNCCLNQ.DATA.LAST_F_VAL_DATE) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_VALDT_GTR_LSTDT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_AC_CHECK() throws IOException,SQLException,Exception {
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 5 
            && (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.trim().length() != 0 
            || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() != 0 
            || WS_VARIABLES.I <= 1); WS_VARIABLES.I += 1) {
            B103_AC_ROUTER_PROC();
            if (pgmRtn) return;
            if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("01) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("03) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B103_CHECK_IA_AC();
                    if (pgmRtn) return;
                }
            } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("04) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NO_GDA_REY, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() == 0 
                    || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_NO2.trim().length() == 0 
                    || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_TYPE2 == ' ' 
                    || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].ISS_DATE2 == 0) {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_MUST_INPUT, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("08) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CASH_CANT_REPAY, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.SET_MET_NOT_RIGHT, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
    }
    public void B090_UPD_STSW_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '4';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        LNCICTLM.FUNC = '2';
        if (WS_COND_FLG.UPD_STSW44_0_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 44 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(44 + 1 - 1);
        }
        if (WS_COND_FLG.UPD_STSW45_0_FLG == 'Y') {
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 45 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(45 + 1 - 1);
        }
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B103_AC_ROUTER_PROC() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AC_FLG2 == ' ') {
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AC_FLG2 = '0';
        }
        WS_COND_FLG.AC_FLG = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AC_FLG2;
        if (WS_COND_FLG.AC_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NO_THEIR_FLG, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.trim().length() > 0 
                && !LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP)) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_REC_AC_TYP, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2 = WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP;
        }
    }
    public void B101_GET_AC_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("IB")) {
            WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP = IB_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD")) {
            WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP = DD_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP = DC_AC;
        } else if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP = INTERNAL;
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_AC_TYP_NOT_MATCH, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B102_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'D';
        AICPQIA.CD.BUSI_KND = "LNPYSUS";
        AICPQIA.BR = LNCCLNQ.DATA.BOOK_BR;
        AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2 = INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2 = AICPQIA.AC;
        CEP.TRC(SCCGWA, AICPQIA.AC);
    }
    public void B103_CHECK_IA_AC() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2, WS_VARIABLES.WS_IA_AC);
        IBS.init(SCCGWA, AICPQITM);
        AICPQITM.INPUT_DATA.COA_FLG = "1";
        AICPQITM.INPUT_DATA.NO = WS_VARIABLES.WS_IA_AC.IA_AC_KEMU;
        S000_CALL_AIZPQITM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQITM.OUTPUT_DATA.TYPE);
        if (AICPQITM.OUTPUT_DATA.TYPE.equalsIgnoreCase("6")) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_IA_AC_OUT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B040_RECALL_LNZSRPC_END() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSRPC);
        LNCSRPC.FUNC_CODE = 'U';
        LNCSRPC.COMM_DATA.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCSRPC.COMM_DATA.TR_VAL_DTE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCSRPC.COMM_DATA.SUBS_FLG = LNCSRPO.COMM_DATA.SUBS_FLG;
        CEP.TRC(SCCGWA, WS_VARIABLES.KOU_TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            WS_VARIABLES.KOU_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        }
        if (WS_VARIABLES.KOU_TOT_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_AC_AMT_ZERO, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.KOU_TOT_AMT < LNCSRPO.COMM_DATA.TOT_AMT) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_LESS_AC_AMT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        LNCSRPC.COMM_DATA.TOT_AMT = WS_VARIABLES.KOU_TOT_AMT;
        LNCSRPC.COMM_DATA.CUR_TRM = LNCSRPO.COMM_DATA.CUR_TRM;
        S000_CALL_LNZSRPC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_DTL_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_P_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_I_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_O_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.TOT_L_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.HRG_AMT);
        CEP.TRC(SCCGWA, LNCSRPC.COMM_DATA.APT_REF);
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            LNCSRPO.COMM_DATA.TOT_AMT = LNCSRPC.COMM_DATA.TOT_AMT;
            LNCSRPO.COMM_DATA.TOT_P_AMT = LNCSRPC.COMM_DATA.TOT_P_AMT;
            LNCSRPO.COMM_DATA.TOT_I_AMT = LNCSRPC.COMM_DATA.TOT_I_AMT;
            LNCSRPO.COMM_DATA.TOT_O_AMT = LNCSRPC.COMM_DATA.TOT_O_AMT;
            LNCSRPO.COMM_DATA.TOT_L_AMT = LNCSRPC.COMM_DATA.TOT_L_AMT;
        }
        WS_VARIABLES.TOT_P_UDAMT = LNCSRPC.COMM_DATA.TOT_P_UDAMT;
        LNCSRPO.COMM_DATA.HRG_AMT = LNCSRPC.COMM_DATA.HRG_AMT;
        if (LNCSRPO.COMM_DATA.TOT_AMT == 0) {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_P_AMT, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B060_SOLD_OUT_LOAN_PROC() throws IOException,SQLException,Exception {
        WS_COND_FLG.PI_PROC_FLG = ' ';
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(37 - 1, 37 + 1 - 1).equalsIgnoreCase("1") 
            && LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            WS_COND_FLG.PI_PROC_FLG = 'P';
            B061_SOLD_OUT_P_PROC();
            if (pgmRtn) return;
        }
        WS_COND_FLG.PI_PROC_FLG = 'I';
        WS_VARIABLES.I_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        WS_VARIABLES.O_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        WS_VARIABLES.L_LEFT_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            B062_SOLD_OUT_I_PROC();
            if (pgmRtn) return;
        } else {
            B063_PURCHASE_BACK_I_PROC();
            if (pgmRtn) return;
        }
        B700_WRITE_B_R_INTTRAN();
        if (pgmRtn) return;
    }
    public void B061_SOLD_OUT_P_PROC() throws IOException,SQLException,Exception {
        B162_SOLD_REPAY_OUT_PROC();
        if (pgmRtn) return;
    }
    public void B062_SOLD_OUT_I_PROC() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_VARIABLES.I_LEFT_AMT > 0 
            || WS_VARIABLES.O_LEFT_AMT > 0 
            || WS_VARIABLES.L_LEFT_AMT > 0)) {
            B161_PURBACK_OWN_OUT_I_PROC();
            if (pgmRtn) return;
            WS_VARIABLES.I_LEFT_AMT -= WS_VARIABLES.PAY_R_I_AMT;
            WS_VARIABLES.O_LEFT_AMT -= WS_VARIABLES.PAY_R_O_AMT;
            WS_VARIABLES.L_LEFT_AMT -= WS_VARIABLES.PAY_R_L_AMT;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_VARIABLES.I_LEFT_AMT > 0 
            || WS_VARIABLES.O_LEFT_AMT > 0 
            || WS_VARIABLES.L_LEFT_AMT > 0)) {
            B160_SOLD_OWN_US_I_PROC();
            if (pgmRtn) return;
            WS_VARIABLES.I_LEFT_AMT -= WS_VARIABLES.PAY_R_I_AMT;
            WS_VARIABLES.O_LEFT_AMT -= WS_VARIABLES.PAY_R_O_AMT;
            WS_VARIABLES.L_LEFT_AMT -= WS_VARIABLES.PAY_R_L_AMT;
        }
        if (WS_VARIABLES.I_LEFT_AMT > 0 
            || WS_VARIABLES.O_LEFT_AMT > 0 
            || WS_VARIABLES.L_LEFT_AMT > 0) {
            WS_VARIABLES.PAY_R_I_AMT = WS_VARIABLES.I_LEFT_AMT;
            WS_VARIABLES.PAY_R_O_AMT = WS_VARIABLES.O_LEFT_AMT;
            WS_VARIABLES.PAY_R_L_AMT = WS_VARIABLES.L_LEFT_AMT;
            B162_SOLD_REPAY_OUT_PROC();
            if (pgmRtn) return;
        }
    }
    public void B063_PURCHASE_BACK_I_PROC() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_VARIABLES.I_LEFT_AMT > 0 
            || WS_VARIABLES.O_LEFT_AMT > 0 
            || WS_VARIABLES.L_LEFT_AMT > 0)) {
            B160_SOLD_OWN_US_I_PROC();
            if (pgmRtn) return;
            WS_VARIABLES.I_LEFT_AMT -= WS_VARIABLES.PAY_R_I_AMT;
            WS_VARIABLES.O_LEFT_AMT -= WS_VARIABLES.PAY_R_O_AMT;
            WS_VARIABLES.L_LEFT_AMT -= WS_VARIABLES.PAY_R_L_AMT;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1") 
            && (WS_VARIABLES.I_LEFT_AMT > 0 
            || WS_VARIABLES.O_LEFT_AMT > 0 
            || WS_VARIABLES.L_LEFT_AMT > 0)) {
            B161_PURBACK_OWN_OUT_I_PROC();
            if (pgmRtn) return;
        }
    }
    public void B064_UPDATE_LNTCTPY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRCTPY.KEY.TR_TYP = WS_VARIABLES.CTPY_TR_TYP;
        T000_READ_LNTCTPY1();
        if (pgmRtn) return;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_VARIABLES.O_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_VARIABLES.O_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_VARIABLES.O_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        if (WS_VARIABLES.O_I_AMT < 0) {
            WS_VARIABLES.O_I_AMT = 0;
        }
        if (WS_VARIABLES.O_O_AMT < 0) {
            WS_VARIABLES.O_O_AMT = 0;
        }
        if (WS_VARIABLES.O_L_AMT < 0) {
            WS_VARIABLES.O_L_AMT = 0;
        }
        if (WS_VARIABLES.I_LEFT_AMT < 0) {
            WS_VARIABLES.I_LEFT_AMT = 0;
        }
        if (WS_VARIABLES.O_LEFT_AMT < 0) {
            WS_VARIABLES.O_LEFT_AMT = 0;
        }
        if (WS_VARIABLES.L_LEFT_AMT < 0) {
            WS_VARIABLES.L_LEFT_AMT = 0;
        }
        if (WS_VARIABLES.I_LEFT_AMT >= WS_VARIABLES.O_I_AMT) {
            WS_VARIABLES.PAY_R_I_AMT = WS_VARIABLES.O_I_AMT;
        } else {
            WS_VARIABLES.PAY_R_I_AMT = WS_VARIABLES.I_LEFT_AMT;
        }
        if (WS_VARIABLES.O_LEFT_AMT >= WS_VARIABLES.O_O_AMT) {
            WS_VARIABLES.PAY_R_O_AMT = WS_VARIABLES.O_O_AMT;
        } else {
            WS_VARIABLES.PAY_R_O_AMT = WS_VARIABLES.O_LEFT_AMT;
        }
        if (WS_VARIABLES.L_LEFT_AMT >= WS_VARIABLES.O_L_AMT) {
            WS_VARIABLES.PAY_R_L_AMT = WS_VARIABLES.O_L_AMT;
        } else {
            WS_VARIABLES.PAY_R_L_AMT = WS_VARIABLES.L_LEFT_AMT;
        }
        LNRCTPY.I_PAY_AMT += WS_VARIABLES.PAY_R_I_AMT;
        LNRCTPY.O_PAY_AMT += WS_VARIABLES.PAY_R_O_AMT;
        LNRCTPY.L_PAY_AMT += WS_VARIABLES.PAY_R_L_AMT;
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B065_UPD_PACK_AND_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPACK);
        LNRPACK.KEY.BTH_PK = LNRCTPY.KEY.BTH_PK;
        T000_READ_UPDATE_LNTPACK();
        if (pgmRtn) return;
        if (WS_COND_FLG.PI_PROC_FLG == 'P') {
            WS_VARIABLES.TX_AC_TYP = LNRPACK.P_AC_TYP;
            WS_VARIABLES.TX_AC = LNRPACK.P_AC;
            WS_VARIABLES.TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            B095_TX_AMT_CR_PROC();
            if (pgmRtn) return;
            LNRPACK.TOT_AMT += LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNRPACK.T_PRIN_AMT += LNCSRPO.COMM_DATA.TOT_P_AMT;
        }
        if (WS_COND_FLG.PI_PROC_FLG == 'I') {
            WS_VARIABLES.TX_AC_TYP = LNRPACK.I_AC_TYP;
            WS_VARIABLES.TX_AC = LNRPACK.I_AC;
            WS_VARIABLES.TX_AMT = WS_VARIABLES.PAY_R_I_AMT;
            WS_VARIABLES.TX_AMT += WS_VARIABLES.PAY_R_O_AMT;
            WS_VARIABLES.TX_AMT += WS_VARIABLES.PAY_R_L_AMT;
            B095_TX_AMT_CR_PROC();
            if (pgmRtn) return;
            LNRPACK.TOT_AMT += WS_VARIABLES.PAY_R_I_AMT;
            LNRPACK.TOT_AMT += WS_VARIABLES.PAY_R_O_AMT;
            LNRPACK.TOT_AMT += WS_VARIABLES.PAY_R_L_AMT;
            LNRPACK.T_INT_AMT += WS_VARIABLES.PAY_R_I_AMT;
            LNRPACK.T_O_INT += WS_VARIABLES.PAY_R_O_AMT;
            LNRPACK.T_L_INT += WS_VARIABLES.PAY_R_L_AMT;
        }
        T000_REWRITE_LNTPACK();
        if (pgmRtn) return;
    }
    public void B066_UPDATE_OR_WRITE_PLDT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRPLDT);
        LNRPLDT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRPLDT.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.KEY.BTH_PK = LNRPACK.KEY.BTH_PK;
        T000_READ_UPDATE_LNTPLDT();
        if (pgmRtn) return;
        if (WS_COND_FLG.READ_LNTPLDT_FLG == 'F') {
            if (WS_COND_FLG.PI_PROC_FLG == 'P') {
                LNRPLDT.T_PAY_AMT += LNCSRPO.COMM_DATA.TOT_P_AMT;
                LNRPLDT.PAY_PRIN += LNCSRPO.COMM_DATA.TOT_P_AMT;
            }
            if (WS_COND_FLG.PI_PROC_FLG == 'I') {
                LNRPLDT.T_PAY_AMT += WS_VARIABLES.PAY_R_I_AMT;
                LNRPLDT.T_PAY_AMT += WS_VARIABLES.PAY_R_O_AMT;
                LNRPLDT.T_PAY_AMT += WS_VARIABLES.PAY_R_L_AMT;
                LNRPLDT.PAY_INT += WS_VARIABLES.PAY_R_I_AMT;
                LNRPLDT.PAY_O_INT += WS_VARIABLES.PAY_R_O_AMT;
                LNRPLDT.PAY_L_INT += WS_VARIABLES.PAY_R_L_AMT;
            }
            T000_REWRITE_LNTPLDT();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, LNRPLDT);
            if (WS_COND_FLG.PI_PROC_FLG == 'P') {
                LNRPLDT.T_PAY_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
                LNRPLDT.PAY_PRIN = LNCSRPO.COMM_DATA.TOT_P_AMT;
                LNRPLDT.PAY_INT = 0;
                LNRPLDT.PAY_O_INT = 0;
                LNRPLDT.PAY_L_INT = 0;
            }
            if (WS_COND_FLG.PI_PROC_FLG == 'I') {
                LNRPLDT.T_PAY_AMT = WS_VARIABLES.PAY_R_I_AMT;
                LNRPLDT.T_PAY_AMT += WS_VARIABLES.PAY_R_O_AMT;
                LNRPLDT.T_PAY_AMT += WS_VARIABLES.PAY_R_L_AMT;
                LNRPLDT.PAY_PRIN = 0;
                LNRPLDT.PAY_INT = WS_VARIABLES.PAY_R_I_AMT;
                LNRPLDT.PAY_O_INT = WS_VARIABLES.PAY_R_O_AMT;
                LNRPLDT.PAY_L_INT = WS_VARIABLES.PAY_R_L_AMT;
            }
            T000_WRITE_LNTPLDT();
            if (pgmRtn) return;
        }
    }
    public void B160_SOLD_OWN_US_I_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.CTPY_TR_TYP = '1';
        B064_UPDATE_LNTCTPY_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_B_I_TEXT.B_I_AMT = WS_VARIABLES.PAY_R_I_AMT;
        WS_VARIABLES.WS_B_I_TEXT.B_O_AMT = WS_VARIABLES.PAY_R_O_AMT;
        WS_VARIABLES.WS_B_I_TEXT.B_L_AMT = WS_VARIABLES.PAY_R_L_AMT;
        if (LNRCTPY.I_PAY_AMT >= LNRCTPY.I_REC_AMT 
            && LNRCTPY.O_PAY_AMT >= LNRCTPY.O_REC_AMT 
            && LNRCTPY.L_PAY_AMT >= LNRCTPY.L_REC_AMT) {
            WS_COND_FLG.UPD_STSW44_0_FLG = 'Y';
            WS_COND_FLG.UPD_ICTL_FLG = 'Y';
        }
    }
    public void B161_PURBACK_OWN_OUT_I_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.CTPY_TR_TYP = '2';
        B064_UPDATE_LNTCTPY_PROC();
        if (pgmRtn) return;
        WS_VARIABLES.WS_R_I_TEXT.R_I_AMT = WS_VARIABLES.PAY_R_I_AMT;
        WS_VARIABLES.WS_R_I_TEXT.R_O_AMT = WS_VARIABLES.PAY_R_O_AMT;
        WS_VARIABLES.WS_R_I_TEXT.R_L_AMT = WS_VARIABLES.PAY_R_L_AMT;
        if (WS_VARIABLES.PAY_R_I_AMT > 0 
            || WS_VARIABLES.PAY_R_O_AMT > 0 
            || WS_VARIABLES.PAY_R_L_AMT > 0) {
            B065_UPD_PACK_AND_CR_PROC();
            if (pgmRtn) return;
            B066_UPDATE_OR_WRITE_PLDT();
            if (pgmRtn) return;
        }
        if (LNRCTPY.I_PAY_AMT >= LNRCTPY.I_REC_AMT 
            && LNRCTPY.O_PAY_AMT >= LNRCTPY.O_REC_AMT 
            && LNRCTPY.L_PAY_AMT >= LNRCTPY.L_REC_AMT) {
            WS_COND_FLG.UPD_STSW45_0_FLG = 'Y';
            WS_COND_FLG.UPD_ICTL_FLG = 'Y';
        }
    }
    public void B162_SOLD_REPAY_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRCTPY.KEY.TR_TYP = '1';
        T000_READ_LNTCTPY1();
        if (pgmRtn) return;
        B065_UPD_PACK_AND_CR_PROC();
        if (pgmRtn) return;
        B066_UPDATE_OR_WRITE_PLDT();
        if (pgmRtn) return;
    }
    public void B700_WRITE_B_R_INTTRAN() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(38 - 1, 38 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.B_I_TRAN_AMT = WS_VARIABLES.WS_B_I_TEXT.B_I_AMT;
            WS_VARIABLES.B_O_TRAN_AMT = WS_VARIABLES.WS_B_I_TEXT.B_O_AMT;
            WS_VARIABLES.B_L_TRAN_AMT = WS_VARIABLES.WS_B_I_TEXT.B_L_AMT;
            WS_VARIABLES.R_I_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT - WS_VARIABLES.WS_B_I_TEXT.B_I_AMT;
            WS_VARIABLES.R_O_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT - WS_VARIABLES.WS_B_I_TEXT.B_O_AMT;
            WS_VARIABLES.R_L_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT - WS_VARIABLES.WS_B_I_TEXT.B_L_AMT;
        } else {
            WS_VARIABLES.R_I_TRAN_AMT = WS_VARIABLES.WS_R_I_TEXT.R_I_AMT;
            WS_VARIABLES.R_O_TRAN_AMT = WS_VARIABLES.WS_R_I_TEXT.R_O_AMT;
            WS_VARIABLES.R_L_TRAN_AMT = WS_VARIABLES.WS_R_I_TEXT.R_L_AMT;
            WS_VARIABLES.B_I_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT - WS_VARIABLES.WS_R_I_TEXT.R_I_AMT;
            WS_VARIABLES.B_O_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT - WS_VARIABLES.WS_R_I_TEXT.R_O_AMT;
            WS_VARIABLES.B_L_TRAN_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT - WS_VARIABLES.WS_R_I_TEXT.R_L_AMT;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if ((WS_VARIABLES.B_I_TRAN_AMT > 0 
            || WS_VARIABLES.B_O_TRAN_AMT > 0 
            || WS_VARIABLES.B_L_TRAN_AMT > 0) 
            && (WS_VARIABLES.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            || WS_VARIABLES.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_VARIABLES.TYPE = 'I';
            WS_VARIABLES.REC_FLAG = 'I';
            WS_VARIABLES.I_AMT = WS_VARIABLES.B_I_TRAN_AMT;
            WS_VARIABLES.O_AMT = WS_VARIABLES.B_O_TRAN_AMT;
            WS_VARIABLES.L_AMT = WS_VARIABLES.B_L_TRAN_AMT;
            WS_VARIABLES.B_I_LENGTH = 480;
            if (WS_VARIABLES.TR_DATA == null) WS_VARIABLES.TR_DATA = "";
            JIBS_tmp_int = WS_VARIABLES.TR_DATA.length();
            for (int i=0;i<1024-JIBS_tmp_int;i++) WS_VARIABLES.TR_DATA += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_B_I_TEXT);
            WS_VARIABLES.TR_DATA = JIBS_tmp_str[0] + WS_VARIABLES.TR_DATA.substring(WS_VARIABLES.B_I_LENGTH);
            B701_TRAN_ADD_PROCESS();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if ((WS_VARIABLES.R_I_TRAN_AMT > 0 
            || WS_VARIABLES.R_O_TRAN_AMT > 0 
            || WS_VARIABLES.R_L_TRAN_AMT > 0) 
            && (WS_VARIABLES.CTL_STSW.substring(44 - 1, 44 + 1 - 1).equalsIgnoreCase("1") 
            || WS_VARIABLES.CTL_STSW.substring(45 - 1, 45 + 1 - 1).equalsIgnoreCase("1"))) {
            WS_VARIABLES.TYPE = 'I';
            WS_VARIABLES.REC_FLAG = 'O';
            WS_VARIABLES.I_AMT = WS_VARIABLES.R_I_TRAN_AMT;
            WS_VARIABLES.O_AMT = WS_VARIABLES.R_O_TRAN_AMT;
            WS_VARIABLES.L_AMT = WS_VARIABLES.R_L_TRAN_AMT;
            WS_VARIABLES.R_I_LENGTH = 480;
            if (WS_VARIABLES.TR_DATA == null) WS_VARIABLES.TR_DATA = "";
            JIBS_tmp_int = WS_VARIABLES.TR_DATA.length();
            for (int i=0;i<1024-JIBS_tmp_int;i++) WS_VARIABLES.TR_DATA += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_R_I_TEXT);
            WS_VARIABLES.TR_DATA = JIBS_tmp_str[0] + WS_VARIABLES.TR_DATA.substring(WS_VARIABLES.R_I_LENGTH);
            B701_TRAN_ADD_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B701_TRAN_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCTRANM);
        IBS.init(SCCGWA, LNRCONT);
        LNCTRANM.FUNC = '0';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRCONT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTCONT();
        if (pgmRtn) return;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = WS_VARIABLES.TYPE;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        LNCTRANM.REC_DATA.TR_CODE = SCCGWA.COMM_AREA.TR_ID.TR_CODE;
        LNCTRANM.REC_DATA.TR_BR = LNRCONT.DOMI_BR;
        LNCTRANM.REC_DATA.TR_DATA = WS_VARIABLES.TR_DATA;
        LNCTRANM.REC_DATA.I_AMT = WS_VARIABLES.I_AMT;
        LNCTRANM.REC_DATA.O_AMT = WS_VARIABLES.O_AMT;
        LNCTRANM.REC_DATA.L_AMT = WS_VARIABLES.L_AMT;
        LNCTRANM.REC_DATA.TRAN_STS = 'N';
        LNCTRANM.REC_DATA.TR_VAL_DTE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCTRANM.REC_DATA.LOAN_STSW = WS_VARIABLES.CTL_STSW;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = WS_VARIABLES.REC_FLAG;
        LNCTRANM.REC_DATA.REQ_FRM_NO = SCCGWA.COMM_AREA.REQ_SYSTEM;
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
    }
    public void B070_CUST_REPAY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCURPN);
        LNCURPN.COMM_DATA.ACM_EVENT = "RLN";
        LNCURPN.COMM_DATA.LN_AC = LNCSRPO.COMM_DATA.CTA_NO;
        LNCURPN.COMM_DATA.SUF_NO = "" + 0;
        JIBS_tmp_int = LNCURPN.COMM_DATA.SUF_NO.length();
        for (int i=0;i<0-JIBS_tmp_int;i++) LNCURPN.COMM_DATA.SUF_NO = "0" + LNCURPN.COMM_DATA.SUF_NO;
        LNCURPN.COMM_DATA.SUBS_FLG = LNCSRPO.COMM_DATA.SUBS_FLG;
        LNCURPN.COMM_DATA.TR_VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_P_UDAMT = WS_VARIABLES.TOT_P_UDAMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        LNCURPN.COMM_DATA.TOT_D_AMTS.TOT_D_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        LNCURPN.COMM_DATA.APT_REF = LNCSRPC.COMM_DATA.APT_REF;
        LNCURPN.COMM_DATA.RDI_FLG = LNCSRPO.COMM_DATA.RDI_FLG;
        LNCURPN.COMM_DATA.RDI_AMT = LNCSRPO.COMM_DATA.RDI_AMT;
        LNCURPN.COMM_DATA.ADJ_TYP = LNCSRPO.COMM_DATA.ADJ_TYP;
        LNCURPN.COMM_DATA.ADJ_AC = LNCSRPO.COMM_DATA.ADJ_AC;
        LNCURPN.COMM_DATA.HRG_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
        LNCURPN.COMM_DATA.CUR_TRM = LNCSRPO.COMM_DATA.CUR_TRM;
        LNCURPN.COMM_DATA.CLN_CUT = LNCSRPO.COMM_DATA.CLN_CUT;
        LNCURPN.COMM_DATA.MMO = LNCSRPO.COMM_DATA.MMO;
        WS_VARIABLES.G = 1;
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 5; WS_VARIABLES.I += 1) {
            if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.trim().length() == 0 
                && LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() == 0) {
                WS_VARIABLES.I = 6;
            } else {
                if (WS_COND_FLG.BZJ_KOUKUAN == 'Y') {
                    if (WS_VARIABLES.I == 1) {
                        LNCURPN.COMM_DATA.ACAMT[1-1].STL_MTH2 = "04";
                        LNCURPN.COMM_DATA.ACAMT[1-1].AC_FLG2 = '0';
                        LNCURPN.COMM_DATA.ACAMT[1-1].REC_AC2 = WS_VARIABLES.BZJ_AC;
                    } else {
                        WS_VARIABLES.K = WS_VARIABLES.I + 1;
                        if (WS_VARIABLES.I < 5) {
                            LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.K-1].STL_MTH2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2;
                            LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.K-1].CHQ_NO2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_NO2;
                            LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.K-1].REC_AC2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
                            LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.K-1].AC_FLG2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AC_FLG2;
                            LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.K-1].PAY_AMT2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2;
                            LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.K-1].AMT_FRM2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AMT_FRM2;
                        }
                    }
                } else {
                    LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2;
                    LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_NO2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_NO2;
                    LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
                    LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AC_FLG2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AC_FLG2;
                    LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2;
                    LNCURPN.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AMT_FRM2 = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].AMT_FRM2;
                }
            }
        }
        S000_CALL_LNZURPN();
        if (pgmRtn) return;
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (WS_VARIABLES.CTL_STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.PART_NO = 0;
            R000_READ_LNTBALZ_PART();
            if (pgmRtn) return;
            WS_VARIABLES.OWE_P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06;
            WS_VARIABLES.OWE_I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22;
            WS_VARIABLES.OWE_O_AMT = LNRBALZ.LOAN_BALL42;
            WS_VARIABLES.OWE_L_AMT = LNRBALZ.LOAN_BALL52;
            IBS.init(SCCGWA, LNCIPAMT);
            LNCIPAMT.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
            IBS.init(SCCGWA, WS_VARIABLES.WS_BEF_PART_DATA);
            LNCIPAMT.P_AMT = LNRBALZ.LOAN_BALL02 + LNRBALZ.LOAN_BALL05 + LNRBALZ.LOAN_BALL06 + LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNCIPAMT.I_AMT = LNRBALZ.LOAN_BALL15 + LNRBALZ.LOAN_BALL20 + LNRBALZ.LOAN_BALL22 + LNCSRPO.COMM_DATA.TOT_I_AMT;
            LNCIPAMT.O_AMT = LNRBALZ.LOAN_BALL42 + LNCSRPO.COMM_DATA.TOT_O_AMT;
            LNCIPAMT.L_AMT = LNRBALZ.LOAN_BALL52 + LNCSRPO.COMM_DATA.TOT_L_AMT;
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_VARIABLES.N = 1; WS_VARIABLES.N <= 10 
                && LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_CTA_NO.trim().length() != 0; WS_VARIABLES.N += 1) {
                WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_P_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_P_AMT;
                WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_I_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_I_AMT;
                WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_O_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_O_AMT;
                WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_L_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_L_AMT;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_P_AMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_I_AMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_O_AMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_L_AMT);
            }
            IBS.init(SCCGWA, WS_VARIABLES.WS_AFT_PART_DATA);
            LNCIPAMT.P_AMT = WS_VARIABLES.OWE_P_AMT;
            LNCIPAMT.I_AMT = WS_VARIABLES.OWE_I_AMT;
            LNCIPAMT.O_AMT = WS_VARIABLES.OWE_O_AMT;
            LNCIPAMT.L_AMT = WS_VARIABLES.OWE_L_AMT;
            LNCIPAMT.F_AMT = WS_VARIABLES.OWE_F_AMT;
            CEP.TRC(SCCGWA, LNCIPAMT.P_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.I_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.O_AMT);
            CEP.TRC(SCCGWA, LNCIPAMT.L_AMT);
            B321_CALL_LNZIPAMT();
            if (pgmRtn) return;
            for (WS_VARIABLES.N = 1; WS_VARIABLES.N <= 10 
                && LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_CTA_NO.trim().length() != 0; WS_VARIABLES.N += 1) {
                WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_P_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_P_AMT;
                WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_I_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_I_AMT;
                WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_O_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_O_AMT;
                WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_L_AMT = LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_L_AMT;
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_P_AMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_I_AMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_O_AMT);
                CEP.TRC(SCCGWA, WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_L_AMT);
            }
            for (WS_VARIABLES.I = 1; LNCSRPC.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SUB_C_NO.trim().length() != 0; WS_VARIABLES.I += 1) {
                if (LNCSRPC.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].I_O_FLG == 'O' 
                    && LNCSRPC.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].LCL_BR == 'N') {
                    IBS.init(SCCGWA, LNRSETL);
                    IBS.init(SCCGWA, LNCRSETL);
                    LNCRSETL.FUNC = 'I';
                    LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
                    LNRSETL.KEY.CI_TYPE = 'P';
                    LNRSETL.KEY.PART_BK = "" + LNCSRPC.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SEQ_NO;
                    JIBS_tmp_int = LNRSETL.KEY.PART_BK.length();
                    for (int i=0;i<8-JIBS_tmp_int;i++) LNRSETL.KEY.PART_BK = "0" + LNRSETL.KEY.PART_BK;
                    LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
                    LNRSETL.KEY.SETTLE_TYPE = 'C';
                    LNRSETL.KEY.SEQ_NO = 0;
                    S000_CALL_LNZRSETL();
                    if (pgmRtn) return;
                    IBS.init(SCCGWA, CICQACRI);
                    CICQACRI.DATA.AGR_NO = LNRSETL.AC;
                    CICQACRI.FUNC = 'A';
                    S000_CALL_CIZQACRI();
                    if (pgmRtn) return;
                    B101_GET_AC_TYPE();
                    if (pgmRtn) return;
                    WS_VARIABLES.TX_AC_TYP = WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP;
                    WS_VARIABLES.TX_AC = LNRSETL.AC;
                    B321_CALL_LNZIPAMT();
                    if (pgmRtn) return;
                    for (WS_VARIABLES.N = 1; LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_CTA_NO.trim().length() != 0; WS_VARIABLES.N += 1) {
                        if (LNRSETL.KEY.PART_BK.equalsIgnoreCase(LNCIPAMT.PART_DATA[WS_VARIABLES.N-1].PART_NO)) {
                            WS_VARIABLES.TX_AMT = WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_P_AMT - WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_P_AMT + WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_I_AMT - WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_I_AMT + WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_O_AMT - WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_O_AMT + WS_VARIABLES.WS_BEF_PART_DATA.WS_BEF_PART_DATAS[WS_VARIABLES.N-1].BEF_PART_L_AMT - WS_VARIABLES.WS_AFT_PART_DATA.WS_AFT_PART_DATAS[WS_VARIABLES.N-1].AFT_PART_L_AMT;
                        }
                    }
                    CEP.TRC(SCCGWA, WS_VARIABLES.TX_AMT);
                    B095_TX_AMT_CR_PROC();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void R000_READ_LNTBALZ_PART() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRBALZ);
        LNRBALZ.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRBALZ.KEY.SUB_CTA_NO = WS_VARIABLES.PART_NO;
        LNTBALZ_RD = new DBParm();
        LNTBALZ_RD.TableName = "LNTBALZ";
        IBS.READ(SCCGWA, LNRBALZ, LNTBALZ_RD);
    }
    public void B321_CALL_LNZIPAMT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-INQ-PAR-AMT", LNCIPAMT);
        if (LNCIPAMT.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIPAMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B080_READ_NEW_ICTL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
    }
    public void B080_DELETE_PLDR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRICTL);
        LNRICTL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRICTL.KEY.SUB_CTA_NO = 0;
        T000_READ_LNTICTL();
        if (pgmRtn) return;
        if (LNRICTL.CTL_STSW == null) LNRICTL.CTL_STSW = "";
        JIBS_tmp_int = LNRICTL.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNRICTL.CTL_STSW += " ";
        if (LNRICTL.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1")) {
            IBS.init(SCCGWA, GDCUMPLD);
            GDCUMPLD.FUNC = 'D';
            GDCUMPLD.ERR_FUNC = 'N';
            GDCUMPLD.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
            S000_CALL_GDZUMPLD();
            if (pgmRtn) return;
        }
    }
    public void B100_CMMT_LIMIT_RELEASE_PROC() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            IBS.init(SCCGWA, LNRCMMT);
            IBS.init(SCCGWA, LNCRCMMT);
            LNRCMMT.KEY.CONTRACT_NO = WS_VARIABLES.FATHER_CONTRACT;
            LNCRCMMT.FUNC = 'I';
            S000_CALL_LNZRCMMT();
            if (pgmRtn) return;
        }
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0 
            && LNRCMMT.REV_CMMT == 'Y' 
            && !JIBS_tmp_str[1].equalsIgnoreCase("LN1538") 
            && LNCRCMMT.RETURN_INFO != 'N') {
            IBS.init(SCCGWA, LNCICRCM);
            LNCICRCM.FUNC = 'P';
            if (LNCSRPO.COMM_DATA.BALANCE == 0) {
                LNCICRCM.DATA.FLG = 'C';
            }
            LNCICRCM.DATA.AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNCICRCM.DATA.TRAN_TYPE = 'R';
            LNCICRCM.DATA.C_T_NO = WS_VARIABLES.FATHER_CONTRACT;
            LNCICRCM.DATA.CONT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            LNCICRCM.DATA.CI_NO = LNCSRPO.COMM_DATA.CI_NO;
            LNCICRCM.DATA.PROD_TYPE = LNCSRPO.COMM_DATA.PROD_CD;
            LNCICRCM.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
            LNCICRCM.DATA.EX_RATE = LNCCLNQ.DATA.EXCHANGE_RATE;
            LNCICRCM.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
            S000_CALL_LNZICRCM();
            if (pgmRtn) return;
        }
    }
    public void B102_GET_AC_BAL() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.AC_TYP.equalsIgnoreCase(DD_AC)
            || WS_VARIABLES.AC_TYP.equalsIgnoreCase(DC_AC)
            || WS_VARIABLES.AC_TYP.equalsIgnoreCase(EB_AC)) {
            B090_GET_DD_AC_BAL();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.AC_TYP.equalsIgnoreCase(INTERNAL)) {
            B920_GET_SUSP_AC_AVABAL();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.AC_TYP.equalsIgnoreCase(IB_AC)) {
            B105_GET_NOS_AC_AVABAL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B920_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_VARIABLES.AC;
        AICPQMIB.INPUT_DATA.CCY = WS_VARIABLES.AC_CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        WS_VARIABLES.AC_AMT = AICPQMIB.OUTPUT_DATA.CBAL;
    }
    public void B105_GET_NOS_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        IBCQINF.INPUT_DATA.NOSTRO_CD = WS_VARIABLES.AC;
        IBCQINF.INPUT_DATA.CCY = WS_VARIABLES.AC_CCY;
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        WS_VARIABLES.AC_AMT = IBCQINF.OUTPUT_DATA.VALUE_BAL;
    }
    public void B110_BP_HISTORY_GEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = LNCSRPO.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.REF_NO = LNCSRPO.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.TX_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCPFHIS.DATA.ACO_AC = LNCSRPO.COMM_DATA.CTA_NO;
        BPCPFHIS.DATA.TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT + LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT + LNCSCLR.COMM_DATA.NOR_P + LNCSCLR.COMM_DATA.NOR_I + LNCSCLR.COMM_DATA.OVR_P + LNCSCLR.COMM_DATA.OVR_I + LNCSCLR.COMM_DATA.NOR_O + LNCSCLR.COMM_DATA.NOR_L;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_AMT);
        BPCPFHIS.DATA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_MMO);
        BPCPFHIS.DATA.CI_NO = LNCSRPO.COMM_DATA.CI_NO;
        BPCPFHIS.DATA.PROD_CD = LNCSRPO.COMM_DATA.PROD_CD;
        if (LNCSRPO.COMM_DATA.ACAMT[1-1].AC_FLG2 == '0') {
            BPCPFHIS.DATA.OTH_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else {
            BPCPFHIS.DATA.RLT_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        }
        if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(") 
            || LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            BPCPFHIS.DATA.TX_TOOL = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        }
        IBS.init(SCCGWA, LNCOD10.TXN_INPUT);
        LNCOD10.TXN_INPUT.CTA_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCOD10.TXN_INPUT.BR = LNCSRPO.COMM_DATA.BR;
        LNCOD10.TXN_INPUT.CI_NO = LNCSRPO.COMM_DATA.CI_NO;
        LNCOD10.TXN_INPUT.CI_ENMS = " ";
        LNCOD10.TXN_INPUT.CITY_CD = " ";
        LNCOD10.TXN_INPUT.PROD_CD = LNCSRPO.COMM_DATA.PROD_CD;
        LNCOD10.TXN_INPUT.CCY = LNCSRPO.COMM_DATA.CCY;
        LNCOD10.TXN_INPUT.LON_PRIN = LNCSRPO.COMM_DATA.PRINCIPAL;
        LNCOD10.TXN_INPUT.LON_BAL = LNCSRPO.COMM_DATA.BALANCE - LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCOD10.TXN_INPUT.TR_VALDT = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        LNCOD10.TXN_INPUT.TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        LNCOD10.TXN_INPUT.TOT_PRIN = LNCSRPO.COMM_DATA.TOT_P_AMT;
        LNCOD10.TXN_INPUT.TOT_INT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        LNCOD10.TXN_INPUT.TOT_PLC = LNCSRPO.COMM_DATA.TOT_O_AMT;
        LNCOD10.TXN_INPUT.WAV_PLC = 0;
        LNCOD10.TXN_INPUT.TOT_ILC = LNCSRPO.COMM_DATA.TOT_L_AMT;
        LNCOD10.TXN_INPUT.WAV_ILC = 0;
        LNCOD10.TXN_INPUT.HRG_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
        LNCOD10.TXN_INPUT.SETL_MTH = LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2;
        if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("01) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.DEP_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.CHQ_NO = LNCSRPO.COMM_DATA.ACAMT[1-1].CHQ_NO2;
        } else if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.SUSP_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else if (LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase("03) LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2.equalsIgnoreCase(")) {
            LNCOD10.TXN_INPUT.NOS_CD = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
        } else {
        }
        LNCOD10.IDX = 0;
        LNCOD10.TXN_INPUT.TAX_AMT = 0;
        BPCPFHIS.DATA.FMT_CODE = "LND10";
        BPCPFHIS.DATA.FMT_LEN = 6054;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, LNCOD10);
        if (BPCPFHIS.DATA.TX_AMT != 0) {
            S000_CALL_BPZPFHIS();
            if (pgmRtn) return;
        }
    }
    public void B130_UPDATE_WAIWEI_INFO() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            IBS.init(SCCGWA, LNRAGRE);
            LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            T000_READ_LNTAGRE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, SCCTPCL);
            SCCTPCL.SERV_AREA.OBJ_SYSTEM = "ESBP";
            SCCTPCL.SERV_AREA.SERV_CODE = "3002200000902";
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ = "" + SCCGWA.COMM_AREA.JRN_NO;
            JIBS_tmp_int = SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ = "0" + SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_SEQ;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CTRNO = LNRAGRE.PAPER_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_DBLNO = LNRAGRE.DRAW_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CCY = LNCSRPO.COMM_DATA.CCY;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT * -1;
            } else {
            }
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CHNL = SCCGWA.COMM_AREA.REQ_SYSTEM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_TYP = LNCSRPO.COMM_DATA.PROD_CD;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_INT = 999;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            if (LNCAPRDM.REC_DATA.PAY_MTH == '0') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-01";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '1') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-02";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '2') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-03";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '3') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-04";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '4') {
                if (LNCAPRDM.REC_DATA.INST_MTH == '1') {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-05";
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                } else {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-06";
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                }
            } else if (LNCAPRDM.REC_DATA.PAY_MTH == '5') {
                SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYTYP = "RPT-07";
                SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            }
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 10 
                && LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SUB_C_NO.trim().length() != 0; WS_VARIABLES.I += 1) {
                if (LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.L-1].LCL_FLG == 'Y') {
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AMT = LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_P_AMT;
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT1 = LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_O_AMT;
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                    SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_FAMT2 = LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_L_AMT;
                    SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
                }
            }
            if (LNCSRPO.COMM_DATA.CUR_TRM == ' ') SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CYCLE = 0;
            else SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CYCLE = Short.parseShort(""+LNCSRPO.COMM_DATA.CUR_TRM);
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_YRATE = LNCCLNQ.DATA.CUR_RAT;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_PAYDT = SCCGWA.COMM_AREA.AC_DATE;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_AC = LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2);
            CEP.TRC(SCCGWA, CICQACRI.DATA.AGR_NO);
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_ACNM = CICQACRI.O_DATA.O_AC_CNM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM = "" + CICQACRI.O_DATA.O_OWNER_BK;
            JIBS_tmp_int = SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM = "0" + SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BKNM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_BK = CICQACRI.O_DATA.O_OWNER_BK;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = LNRAGRE.PAPER_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_CUSTNM = CICCUST.O_DATA.O_CI_NM;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_IDTYP = CICCUST.O_DATA.O_ID_TYPE;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.INP_AREA.BD_PAYB_NOTICE.PAYB_ID = CICCUST.O_DATA.O_ID_NO;
            SCCTPCL.INP_AREA.SERV_DATA = IBS.CLS2CPY(SCCGWA, SCCTPCL.INP_AREA.BD_PAYB_NOTICE);
            SCCTPCL.SERV_AREA.SERV_TYPE = 'N';
            SCCTPCL.INP_AREA.SERV_DATA_LEN = 5395;
            S000_CALL_SCZTPCL();
            if (pgmRtn) return;
        }
    }
    public void B120_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "LND10";
        SCCFMT.DATA_PTR = LNCOD10;
        SCCFMT.DATA_LEN = 6054;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B050_BATCH_AMT_DEBIT_PROC() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.SUBS_FLG == 'Y') {
            T00_READ_LNTRELA();
            if (pgmRtn) return;
            B052_SUBS_LOAN_PROC();
            if (pgmRtn) return;
        } else {
            WS_VARIABLES.PAY_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
            if (LNCAPRDM.REC_DATA.GDA_AUTO_DB == 'Y') {
                if (LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0 
                    && LNCAPRDM.REC_DATA.GDA_DB_SEQ == 'G') {
                    B053_BAOZHENGJIN_KOUKUAN();
                    if (pgmRtn) return;
                    WS_COND_FLG.BZJ_KOUKUAN = 'Y';
                    if (WS_VARIABLES.PAY_AMT > 0) {
                        B051_AMT_DEBIT_PROC();
                        if (pgmRtn) return;
                    }
                }
                if (LNCAPRDM.REC_DATA.GDA_DB_SEQ == 'S' 
                    && LNCAPRDM.REC_DATA.GDA_APRE.trim().length() > 0) {
                    B051_AMT_DEBIT_PROC();
                    if (pgmRtn) return;
                    if (WS_VARIABLES.PAY_AMT > 0) {
                        B053_BAOZHENGJIN_KOUKUAN();
                        if (pgmRtn) return;
                        WS_COND_FLG.BZJ_KOUKUAN = 'Y';
                    }
                }
            } else {
                B051_AMT_DEBIT_PROC();
                if (pgmRtn) return;
            }
            if (WS_VARIABLES.PAY_AMT != 0) {
                WS_VARIABLES.KOU_TOT_AMT = WS_VARIABLES.KOU_SETL_AMT + WS_VARIABLES.KOU_GDA_TOT_AMT;
            } else {
                WS_VARIABLES.KOU_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
            }
        }
        B040_RECALL_LNZSRPC_END();
        if (pgmRtn) return;
    }
    public void B053_BAOZHENGJIN_KOUKUAN() throws IOException,SQLException,Exception {
        WS_VARIABLES.KOU_KUAN_AMT = WS_VARIABLES.PAY_AMT;
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() == 0) {
                LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2 = " ";
                for (WS_VARIABLES.G = 1; WS_VARIABLES.G <= GDCIQLDR.TCNT 
                    && WS_VARIABLES.KOU_KUAN_AMT != 0; WS_VARIABLES.G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT);
                    if (GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY.equalsIgnoreCase(LNCSRPO.COMM_DATA.CCY)) {
                        if (WS_VARIABLES.KOU_KUAN_AMT > GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT) {
                            WS_VARIABLES.KOU_GDA_AMT = GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT;
                        } else {
                            WS_VARIABLES.KOU_GDA_AMT = WS_VARIABLES.KOU_KUAN_AMT;
                        }
                        IBS.init(SCCGWA, GDCSTRAC);
                        GDCSTRAC.TXFUCTYP = '1';
                        GDCSTRAC.TXRSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                        GDCSTRAC.TXAMT = WS_VARIABLES.KOU_GDA_AMT;
                        if (GDCSTRAC.TXAMT != 0) {
                            S000_CALL_GDZSTRAC();
                            if (pgmRtn) return;
                            WS_VARIABLES.BZJ_AC = GDCSTRAC.TXAC;
                        }
                        WS_VARIABLES.KOU_GDA_TOT_AMT += GDCSTRAC.TXAMT;
                        WS_VARIABLES.KOU_KUAN_AMT -= GDCSTRAC.TXAMT;
                    }
                }
                WS_VARIABLES.KOU_KUAN_AMT = WS_VARIABLES.KOU_GDA_TOT_AMT;
            } else {
                WS_COND_FLG.KOU_GDA_FLAG = 'N';
                for (WS_VARIABLES.G = 1; WS_VARIABLES.G <= GDCIQLDR.TCNT 
                    && WS_COND_FLG.KOU_GDA_FLAG != 'Y'; WS_VARIABLES.G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT);
                    if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.equalsIgnoreCase(GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC) 
                        && LNCSRPO.COMM_DATA.CCY.equalsIgnoreCase(GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY) 
                        && GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT != 0) {
                        WS_COND_FLG.KOU_GDA_FLAG = 'Y';
                        if (GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT < WS_VARIABLES.KOU_KUAN_AMT) {
                            WS_VARIABLES.KOU_KUAN_AMT = GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT;
                        } else {
                            WS_VARIABLES.KOU_GDA_AMT = WS_VARIABLES.KOU_KUAN_AMT;
                        }
                        IBS.init(SCCGWA, GDCSTRAC);
                        GDCSTRAC.TXFUCTYP = '1';
                        GDCSTRAC.TXRSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                        GDCSTRAC.TXAMT = WS_VARIABLES.KOU_GDA_AMT;
                        if (GDCSTRAC.TXAMT != 0) {
                            S000_CALL_GDZSTRAC();
                            if (pgmRtn) return;
                            WS_VARIABLES.BZJ_AC = GDCSTRAC.TXAC;
                        }
                        WS_VARIABLES.KOU_KUAN_AMT = GDCSTRAC.TXAMT;
                    }
                }
                if (WS_COND_FLG.KOU_GDA_FLAG == 'N') {
                    WS_VARIABLES.KOU_KUAN_AMT = 0;
                }
            }
        } else {
        }
    }
    public void B101_JY_CDD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCICTLM);
        LNCICTLM.FUNC = '3';
        LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
        S000_CALL_LNZICTLM();
        if (pgmRtn) return;
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
        JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
        if (LNCICTLM.REC_DATA.CTL_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase("1") 
            && LNCICTLM.REC_DATA.CTL_STSW.substring(20 - 1, 20 + 1 - 1).equalsIgnoreCase("1")) {
            LNCICTLM.FUNC = '4';
            LNCICTLM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
            IBS.init(SCCGWA, DCCSIALP);
            DCCSIALP.FUNC = 'D';
            DCCSIALP.LN_AC = LNCSRPO.COMM_DATA.CTA_NO;
            DCCSIALP.LN_TYP = '2';
            S000_CALL_DCZSIALP();
            if (pgmRtn) return;
            LNCICTLM.REC_DATA.KEY.SUB_CTA_NO = 0;
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
            LNCICTLM.FUNC = '2';
            if (LNCICTLM.REC_DATA.CTL_STSW == null) LNCICTLM.REC_DATA.CTL_STSW = "";
            JIBS_tmp_int = LNCICTLM.REC_DATA.CTL_STSW.length();
            for (int i=0;i<128-JIBS_tmp_int;i++) LNCICTLM.REC_DATA.CTL_STSW += " ";
            LNCICTLM.REC_DATA.CTL_STSW = LNCICTLM.REC_DATA.CTL_STSW.substring(0, 20 - 1) + "0" + LNCICTLM.REC_DATA.CTL_STSW.substring(20 + 1 - 1);
            S000_CALL_LNZICTLM();
            if (pgmRtn) return;
        }
    }
    public void C000_CALL_DDZUOBAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, LNRLOAN.CTL_TYPE);
        if (LNRLOAN.CTL_TYPE == '8') {
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TOT_P_AMT);
            IBS.init(SCCGWA, DDCUOBAL);
            DDCUOBAL.AC = LNCSRPO.COMM_DATA.CTA_NO;
            DDCUOBAL.OD_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            S000_CALL_DDZUOBAL();
            if (pgmRtn) return;
        } else {
        }
    }
    public void S000_CALL_DCZSIALP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-AUTO-LN-PLAN", DCCSIALP);
    }
    public void B051_AMT_GDA_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.KOU_KUAN_AMT = WS_VARIABLES.PAY_AMT;
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= GDCIQLDR.TCNT 
                && WS_VARIABLES.PAY_AMT != 0; WS_VARIABLES.I += 1) {
                if (GDCIQLDR.INFO[WS_VARIABLES.I-1].CCY.equalsIgnoreCase(LNCSRPO.COMM_DATA.CCY)) {
                    IBS.init(SCCGWA, GDCSDDDR);
                    GDCUMPDR.INPUT.FUNC = 'D';
                    GDCSDDDR.DRAC = GDCIQLDR.INFO[WS_VARIABLES.I-1].OUT_AC;
                    GDCSDDDR.AMT = WS_VARIABLES.KOU_GDA_AMT;
                    GDCSDDDR.CCY = LNCSRPO.COMM_DATA.CCY;
                    GDCSDDDR.STLT = ' ';
                    GDCSDDDR.CRAC = LNRAGRE.PAPER_NO;
                    if (GDCIQLDR.INFO[WS_VARIABLES.I-1].RAMT < WS_VARIABLES.KOU_KUAN_AMT) {
                        GDCSDDDR.AMT = GDCIQLDR.INFO[WS_VARIABLES.I-1].RAMT;
                    } else {
                        GDCSDDDR.AMT = WS_VARIABLES.KOU_KUAN_AMT;
                    }
                    if (GDCSDDDR.AMT != 0) {
                        WS_VARIABLES.SEQ2 += 1;
                        S000_CALL_GDZSDDDR();
                        if (pgmRtn) return;
                        if (GDCSDDDR.AMT == 0) {
                            WS_VARIABLES.SEQ2 -= 1;
                        } else {
                            IBS.init(SCCGWA, WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ2-1]);
                            WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ2-1].GDA_AC1 = GDCIQLDR.INFO[WS_VARIABLES.I-1].OUT_AC;
                            WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ2-1].GDA_CCY1 = GDCIQLDR.INFO[WS_VARIABLES.I-1].CCY;
                            WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ2-1].GDA_PAY_AMT1 = GDCSDDDR.AMT;
                        }
                    }
                    WS_VARIABLES.KOU_GDA_TOT_AMT += GDCSDDDR.AMT;
                    WS_VARIABLES.KOU_KUAN_AMT = GDCSDDDR.AMT;
                    if (WS_VARIABLES.KOU_KUAN_AMT <= WS_VARIABLES.PAY_AMT) {
                        WS_VARIABLES.PAY_AMT -= WS_VARIABLES.KOU_KUAN_AMT;
                        WS_VARIABLES.KOU_KUAN_AMT = WS_VARIABLES.PAY_AMT;
                    }
                }
            }
        } else {
        }
    }
    public void B051_AMT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, DDCUCRAC);
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        T000_READ_LNTSETL();
        if (pgmRtn) return;
        WS_VARIABLES.TX_AC_TYP = LNRSETL.AC_TYP;
        WS_VARIABLES.TX_AC = LNRSETL.AC;
        WS_VARIABLES.TX_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B051_AMT_DEBIT_PROC() throws IOException,SQLException,Exception {
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 5 
            && (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.trim().length() != 0 
            || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() != 0 
            || WS_VARIABLES.I <= 1); WS_VARIABLES.I += 1) {
            if (WS_VARIABLES.PAY_AMT != 0) {
                WS_VARIABLES.KOU_KUAN_AMT = WS_VARIABLES.PAY_AMT;
                if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2 != 0 
                    && LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2 < WS_VARIABLES.KOU_KUAN_AMT) {
                    WS_VARIABLES.KOU_KUAN_AMT = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2;
                }
                CEP.TRC(SCCGWA, WS_VARIABLES.I);
                CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2);
                if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("01) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                    || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                    || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")
                    || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_DD_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("02) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_GLSUS_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("03) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_NOSCD_DEBIT_PROC();
                    if (pgmRtn) return;
                } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("04) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                    B052_GD_DEBIT_PROC();
                    if (pgmRtn) return;
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_NO_GDA_REY, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("08) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
                    IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_CASH_CANT_REPAY, WS_VARIABLES.WS_ERR_MSG);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                } else {
                }
                CEP.TRC(SCCGWA, WS_VARIABLES.KOU_KUAN_AMT);
                if (WS_VARIABLES.KOU_KUAN_AMT <= WS_VARIABLES.PAY_AMT) {
                    WS_VARIABLES.PAY_AMT -= WS_VARIABLES.KOU_KUAN_AMT;
                }
            } else {
                WS_VARIABLES.KOU_KUAN_AMT = 0;
            }
            LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2 = WS_VARIABLES.KOU_KUAN_AMT;
            WS_VARIABLES.KOU_SETL_AMT += WS_VARIABLES.KOU_KUAN_AMT;
        }
    }
    public void B052_DD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'Y';
        DDCUDRAC.TSTS_TABL = "0014";
        DDCUDRAC.BV_TYP = '3';
        if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("05) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(") 
            || LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("06) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
            DDCUDRAC.CARD_NO = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
        } else {
            DDCUDRAC.AC = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
        }
        if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_NO2.trim().length() > 0 
            && LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase("07) LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2.equalsIgnoreCase(")) {
            DDCUDRAC.CHQ_NO = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_NO2;
            DDCUDRAC.CHQ_TYPE = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CHQ_TYPE2;
            DDCUDRAC.CHQ_ISS_DATE = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].ISS_DATE2;
            DDCUDRAC.PAY_PSWD = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_PSWD;
        }
        DDCUDRAC.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.CCY_TYPE = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].CCY_TYP;
        DDCUDRAC.TX_AMT = WS_VARIABLES.KOU_KUAN_AMT;
        DDCUDRAC.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP")) {
            DDCUDRAC.TX_MMO = "D106";
        } else {
            DDCUDRAC.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        }
        DDCUDRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        DDCUDRAC.OTHER_CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.OTHER_AMT = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].PAY_AMT2;
        DDCUDRAC.NARRATIVE = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TRF_NARRATIVE);
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.KOU_KUAN_AMT = DDCUDRAC.TX_AMT;
    }
    public void B090_GET_DD_AC_BAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSQBAL);
        DDCSQBAL.AC_NO = WS_VARIABLES.AC;
        DDCSQBAL.CCY = WS_VARIABLES.AC_CCY;
        S000_CALL_DDZSQBAL();
        if (pgmRtn) return;
        WS_VARIABLES.AC_AMT = DDCSQBAL.AVL_BAL;
    }
    public void B052_GLSUS_DEBIT_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.AC = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
        WS_VARIABLES.AC_CCY = LNCSRPO.COMM_DATA.CCY;
        B042_GET_SUSP_AC_AVABAL();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        if (AICPQMIB.OUTPUT_DATA.BAL_RFLG == 'N' 
            && WS_VARIABLES.PQMIB_CBAL < WS_VARIABLES.KOU_KUAN_AMT) {
            AICUUPIA.DATA.AMT = WS_VARIABLES.PQMIB_CBAL;
        } else {
            AICUUPIA.DATA.AMT = WS_VARIABLES.KOU_KUAN_AMT;
        }
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].RVS_NO2;
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
        WS_VARIABLES.KOU_KUAN_AMT = AICUUPIA.DATA.AMT;
    }
    public void B052_NOSCD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
        IBCPOSTA.CCY = LNCSRPO.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_VARIABLES.KOU_KUAN_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSRPO.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.NARR = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_TRF_NARRATIVE);
        IBCPOSTA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
        WS_VARIABLES.KOU_KUAN_AMT = IBCPOSTA.AMT;
    }
    public void B052_GM_DEBIT_PROC() throws IOException,SQLException,Exception {
        B044_GET_GDA_AC_AVABAL();
        if (pgmRtn) return;
        if (CICQACAC.O_DATA.O_ACR_DATA.O_FRM_APP_ACR.equalsIgnoreCase("DD")) {
            if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.trim().length() == 0) {
                LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2 = " ";
                for (WS_VARIABLES.G = 1; WS_VARIABLES.G <= GDCIQLDR.TCNT 
                    && WS_VARIABLES.KOU_KUAN_AMT != 0; WS_VARIABLES.G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT);
                    if (GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY.equalsIgnoreCase(LNCSRPO.COMM_DATA.CCY)) {
                        if (WS_VARIABLES.KOU_KUAN_AMT > GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT) {
                            WS_VARIABLES.KOU_GDA_AMT = GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT;
                        } else {
                            WS_VARIABLES.KOU_GDA_AMT = WS_VARIABLES.KOU_KUAN_AMT;
                        }
                        IBS.init(SCCGWA, GDCUMPDR);
                        GDCUMPDR.INPUT.FUNC = 'D';
                        GDCUMPDR.INPUT.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                        GDCUMPDR.INPUT.AC = GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC;
                        GDCUMPDR.INPUT.AMT = WS_VARIABLES.KOU_GDA_AMT;
                        if (GDCUMPDR.INPUT.AMT != 0) {
                            WS_VARIABLES.SEQ1 += 1;
                            GDCUMPDR.INPUT.SEQ_NO = WS_VARIABLES.SEQ1;
                            S000_CALL_GDZUMPDR();
                            if (pgmRtn) return;
                            if (GDCUMPDR.INPUT.AMT == 0) {
                                WS_VARIABLES.SEQ2 -= 1;
                            } else {
                                IBS.init(SCCGWA, WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ1-1]);
                                WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ1-1].GDA_AC1 = GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC;
                                WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ1-1].GDA_CCY1 = GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY;
                                WS_VARIABLES.WS_GDA_ACAMT[WS_VARIABLES.SEQ1-1].GDA_PAY_AMT1 = GDCUMPDR.INPUT.AMT;
                            }
                        }
                        WS_VARIABLES.KOU_GDA_TOT_AMT += GDCUMPDR.INPUT.AMT;
                        WS_VARIABLES.KOU_KUAN_AMT -= GDCUMPDR.INPUT.AMT;
                    }
                }
                WS_VARIABLES.KOU_KUAN_AMT = WS_VARIABLES.KOU_GDA_TOT_AMT;
            } else {
                WS_COND_FLG.KOU_GDA_FLAG = 'N';
                for (WS_VARIABLES.G = 1; WS_VARIABLES.G <= GDCIQLDR.TCNT 
                    && WS_COND_FLG.KOU_GDA_FLAG != 'Y'; WS_VARIABLES.G += 1) {
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY);
                    CEP.TRC(SCCGWA, GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT);
                    if (LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2.equalsIgnoreCase(GDCIQLDR.INFO[WS_VARIABLES.G-1].OUT_AC) 
                        && LNCSRPO.COMM_DATA.CCY.equalsIgnoreCase(GDCIQLDR.INFO[WS_VARIABLES.G-1].CCY) 
                        && GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT != 0) {
                        WS_COND_FLG.KOU_GDA_FLAG = 'Y';
                        if (GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT < WS_VARIABLES.KOU_KUAN_AMT) {
                            WS_VARIABLES.KOU_KUAN_AMT = GDCIQLDR.INFO[WS_VARIABLES.G-1].RAMT;
                        } else {
                        }
                        IBS.init(SCCGWA, GDCUMPDR);
                        GDCUMPDR.INPUT.FUNC = 'D';
                        GDCUMPDR.INPUT.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
                        GDCUMPDR.INPUT.AC = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].REC_AC2;
                        GDCUMPDR.INPUT.AMT = WS_VARIABLES.KOU_KUAN_AMT;
                        if (GDCUMPDR.INPUT.AMT != 0) {
                            S000_CALL_GDZUMPDR();
                            if (pgmRtn) return;
                        }
                        WS_VARIABLES.KOU_KUAN_AMT = GDCUMPDR.INPUT.AMT;
                    }
                }
                if (WS_COND_FLG.KOU_GDA_FLAG == 'N') {
                    WS_VARIABLES.KOU_KUAN_AMT = 0;
                }
            }
        } else {
        }
    }
    public void B052_GD_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCSDDDR);
        GDCSDDDR.DRAC = LNCSRPO.COMM_DATA.ACAMT[WS_VARIABLES.I-1].STL_MTH2;
        GDCSDDDR.CCY = LNCSRPO.COMM_DATA.CCY;
        GDCSDDDR.CCY_TYP = LNCSRPO.COMM_DATA.CCY_TYPE.charAt(0);
        GDCSDDDR.AMT = WS_VARIABLES.KOU_KUAN_AMT;
        GDCSDDDR.CRAC = LNRAGRE.PAPER_NO;
        S000_CALL_GDZSDDDR();
        if (pgmRtn) return;
    }
    public void B044_GET_GDA_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, GDCIQLDR);
        IBS.init(SCCGWA, CICQACAC);
        GDCIQLDR.RSEQ = LNCAPRDM.REC_DATA.GDA_APRE;
        S000_CALL_GDZIQLDR();
        if (pgmRtn) return;
        CICQACAC.FUNC = 'R';
        CICQACAC.DATA.AGR_NO = GDCIQLDR.INFO[1-1].OUT_AC;
        CICQACAC.DATA.AGR_SEQ = GDCIQLDR.INFO[1-1].OUT_SEQ;
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
    }
    public void B052_CASH_DEBIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = LNCSRPO.COMM_DATA.CCY;
        BPCUABOX.CCY_TYP = '0';
        BPCUABOX.AMT = WS_VARIABLES.KOU_KUAN_AMT;
        BPCUABOX.OPP_AC = LNCSRPO.COMM_DATA.CTA_NO;
        BPCUABOX.RMK = "LOAN PRE-REPAYMENT";
        if (BPCUABOX.AMT != 0) {
            S000_CALL_BPZUABOX();
            if (pgmRtn) return;
        }
        WS_VARIABLES.KOU_KUAN_AMT = BPCUABOX.AMT;
    }
    public void B052_SUBS_LOAN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSUBS);
        LNRSUBS.KEY.PROJ_NO = LNRRELA.PROJ_NO;
        T000_READ_LNTSUBS();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            if (LNRSUBS.PAY_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_SUBS_PAY_AC_EMPTY, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.TX_AC = LNRSUBS.PAY_AC;
            WS_VARIABLES.TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        }
        LNCSRPO.COMM_DATA.ACAMT[1-1].REC_AC2 = LNRSUBS.PAY_AC;
        LNCSRPO.COMM_DATA.ACAMT[1-1].STL_MTH2 = INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[1-1].PAY_AMT2 = LNCSRPO.COMM_DATA.TOT_P_AMT;
        WS_VARIABLES.TOT_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        if (WS_VARIABLES.TOT_I_AMT > 0) {
            if (LNRSUBS.IA_AC.trim().length() == 0) {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_SUBS_IA_AC_EMPTY, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            WS_VARIABLES.TX_AC = LNRSUBS.IA_AC;
            WS_VARIABLES.TX_AMT = WS_VARIABLES.TOT_I_AMT;
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        }
        LNCSRPO.COMM_DATA.ACAMT[2-1].REC_AC2 = LNRSUBS.IA_AC;
        LNCSRPO.COMM_DATA.ACAMT[2-1].STL_MTH2 = INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[2-1].PAY_AMT2 = WS_VARIABLES.TOT_I_AMT;
        WS_VARIABLES.KOU_KUAN_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        WS_VARIABLES.KOU_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        WS_VARIABLES.PAY_AMT = 0;
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 10 
            && LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SUB_C_NO.trim().length() != 0; WS_VARIABLES.I += 1) {
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SUB_C_NO = LNRPARS.SUB_LN_AC;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].BBR = LNRPARS.BOOK_BR;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SEQ_NO = "" + LNRPARS.KEY.SEQ_NO;
            JIBS_tmp_int = LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SEQ_NO.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SEQ_NO = "0" + LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].SEQ_NO;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].REL_TYP = LNRPARS.REL_TYPE;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_P_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT * LNRPARS.PARTI_PCT / 100;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT * LNRPARS.PARTI_PCT / 100;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT * LNRPARS.PARTI_PCT / 100;
            LNCSRPO.COMM_DATA.PART_DATA[WS_VARIABLES.I-1].PY_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT * LNRPARS.PARTI_PCT / 100;
        }
    }
    public void B052_SUBS_DEBIT_PROC_AI() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQIA);
        AICPQIA.SIGN = 'D';
        AICPQIA.CD.BUSI_KND = "LNSUBAC";
        AICPQIA.BR = LNCCLNQ.DATA.BOOK_BR;
        AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
        S000_CALL_AIZPQIA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQIA.AC);
        CEP.TRC(SCCGWA, LNRSUBS.SUS_AC_SEQ);
        IBS.CPY2CLS(SCCGWA, AICPQIA.AC, WS_VARIABLES.WS_IA_AC);
        if (LNRSUBS.SUS_AC_SEQ != 0) {
            WS_VARIABLES.WS_IA_AC.IA_AC_NO = LNRSUBS.SUS_AC_SEQ;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_IA_AC);
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_IA_AC);
        CEP.TRC(SCCGWA, LNRSUBS.POST_CREV_NO);
        if (LNRSUBS.POST_CREV_NO.trim().length() > 0) {
            AICUUPIA.DATA.RVS_NO = LNRSUBS.POST_CREV_NO;
        }
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_VARIABLES.SUBS_TOT_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIB();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        CEP.TRC(SCCGWA, LNRSUBS.POST_CREV_NO);
        if (LNRSUBS.POST_CREV_NO.trim().length() == 0 
            || !AICUUPIA.DATA.RVS_NO.equalsIgnoreCase(LNRSUBS.POST_CREV_NO)) {
            LNRSUBS.POST_CREV_NO = AICUUPIA.DATA.RVS_NO;
        }
        LNCSRPO.COMM_DATA.ACAMT[2-1].STL_MTH2 = INTERNAL;
        LNCSRPO.COMM_DATA.ACAMT[2-1].REC_AC2 = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_IA_AC);
        LNCSRPO.COMM_DATA.ACAMT[2-1].PAY_AMT2 = WS_VARIABLES.SUBS_TOT_AMT;
    }
    public void B042_GET_SUSP_AC_AVABAL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICPQMIB);
        AICPQMIB.INPUT_DATA.AC = WS_VARIABLES.AC;
        AICPQMIB.INPUT_DATA.CCY = WS_VARIABLES.AC_CCY;
        S000_CALL_AIZPQMIB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.CBAL);
        CEP.TRC(SCCGWA, AICPQMIB.OUTPUT_DATA.BAL_RFLG);
        if (AICPQMIB.OUTPUT_DATA.CBAL < 0) {
            WS_VARIABLES.PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL * ( -1 );
        } else {
            WS_VARIABLES.PQMIB_CBAL = AICPQMIB.OUTPUT_DATA.CBAL;
        }
    }
    public void B052_TRUS_HRGTAX_PROC() throws IOException,SQLException,Exception {
        B451_GET_TRUS_SETL_INF();
        if (pgmRtn) return;
        B453_TRUS_I_SETL_PROCESS();
        if (pgmRtn) return;
    }
    public void B451_GET_TRUS_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = 'A';
        LNRSETL.KEY.SEQ_NO = 0;
        S000_CALL_LNZRSETL_2();
        if (pgmRtn) return;
        WS_VARIABLES.WS_SETL_INFO.TRUS_P_AC_TYP = LNRSETL.AC_TYP;
        WS_VARIABLES.WS_SETL_INFO.TRUS_P_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = 'B';
        LNRSETL.KEY.SEQ_NO = 0;
        S000_CALL_LNZRSETL_2();
        if (pgmRtn) return;
        WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC_TYP = LNRSETL.AC_TYP;
        WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC = LNRSETL.AC;
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CI_TYPE = 'C';
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '4';
        LNRSETL.KEY.SEQ_NO = 0;
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        if (WS_VARIABLES.WS_SETL_INFO.TRUS_P_AC.trim().length() == 0) {
            WS_VARIABLES.WS_SETL_INFO.TRUS_P_AC_TYP = LNRSETL.AC_TYP;
            WS_VARIABLES.WS_SETL_INFO.TRUS_P_AC = LNRSETL.AC;
        }
        if (WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC.trim().length() == 0) {
            WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC_TYP = LNRSETL.AC_TYP;
            WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC = LNRSETL.AC;
        }
    }
    public void B453_TRUS_I_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_VARIABLES.WS_SETL_INFO.TRUS_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        WS_VARIABLES.TX_AC_TYP = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC_TYP;
        WS_VARIABLES.TX_AC = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC;
        WS_VARIABLES.TX_AMT = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AMT;
        if (WS_VARIABLES.WS_SETL_INFO.TRUS_I_AMT > 0) {
            B095_TX_AMT_CR_PROC();
            if (pgmRtn) return;
        }
        if (WS_VARIABLES.CTL_STSW == null) WS_VARIABLES.CTL_STSW = "";
        JIBS_tmp_int = WS_VARIABLES.CTL_STSW.length();
        for (int i=0;i<128-JIBS_tmp_int;i++) WS_VARIABLES.CTL_STSW += " ";
        if (!WS_VARIABLES.CTL_STSW.substring(54 - 1, 54 + 1 - 1).equalsIgnoreCase("1")) {
            WS_VARIABLES.AC_TYP = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC_TYP;
            WS_VARIABLES.AC = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC;
            WS_VARIABLES.AC_CCY = LNCSRPO.COMM_DATA.CCY;
            B102_GET_AC_BAL();
            if (pgmRtn) return;
            if (WS_VARIABLES.AC_AMT >= LNCSRPO.COMM_DATA.HRG_AMT) {
                WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
            } else {
                LNCSRPO.COMM_DATA.HRG_AMT = WS_VARIABLES.AC_AMT;
                WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT = LNCSRPO.COMM_DATA.HRG_AMT;
            }
            if (WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT != 0) {
                B454_TRUS_FEE_CHARGE_PROCESS();
                if (pgmRtn) return;
            }
        }
    }
    public void B454_TRUS_FEE_CHARGE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFSVR);
        BPCOFSVR.FUNC = 'I';
        BPCOFSVR.KEY.SVR_NO = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        S000_CALL_BPZFSSVR();
        if (pgmRtn) return;
        for (WS_VARIABLES.I = 1; WS_VARIABLES.I <= 20 
            && BPCOFSVR.VAL.DATA[WS_VARIABLES.I-1].FEE_CODE.trim().length() != 0; WS_VARIABLES.I += 1) {
            CEP.TRC(SCCGWA, BPCOFSVR.VAL.DATA[WS_VARIABLES.I-1].PROD_CODE);
            CEP.TRC(SCCGWA, BPCOFSVR.VAL.DATA[WS_VARIABLES.I-1].FEE_CODE);
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.PROD_CD);
            if (BPCOFSVR.VAL.DATA[WS_VARIABLES.I-1].PROD_CODE.equalsIgnoreCase(LNCSRPO.COMM_DATA.PROD_CD)) {
                WS_VARIABLES.FEE_CODE_HRG = BPCOFSVR.VAL.DATA[WS_VARIABLES.I-1].FEE_CODE;
            }
        }
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC;
        if (CICACCU.DATA.AGR_NO.trim().length() > 0) {
            S000_CALL_CIZACCU();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFFTXI);
        JIBS_NumStr = "" + 0;
        BPCFFTXI.TX_DATA.AUH_FLG = JIBS_NumStr.charAt(0);
        BPCFFTXI.TX_DATA.CHG_AC_INFO.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.AC_TYP = '0';
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_AC = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC;
        BPCFFTXI.TX_DATA.CHG_AC_INFO.CHG_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCFFTXI.TX_DATA.SVR_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCFFTXI.TX_DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCFFTXI.TX_DATA.FEE_CTRT = " ";
        BPCFFTXI.TX_DATA.BSNS_NO = LNCSRPO.COMM_DATA.CTA_NO;
        BPCFFTXI.TX_DATA.PROC_TYPE = '0';
        S000_CALL_BPZFFTXI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCFFCON);
        BPCFFCON.FEE_INFO.FEE_CNT = 1;
        CEP.TRC(SCCGWA, WS_VARIABLES.FEE_CODE_HRG);
        CEP.TRC(SCCGWA, WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT);
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CODE = WS_VARIABLES.FEE_CODE_HRG;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_BAS = WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_AMT = WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_FLG = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC_TY = '0';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AC = WS_VARIABLES.WS_SETL_INFO.TRUS_I_AC;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].FEE_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_CCY = LNCSRPO.COMM_DATA.CCY;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_BAS = WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].CHG_AMT = WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].ADJ_AMT = WS_VARIABLES.WS_SETL_INFO.TRUS_F_AMT;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_FLG = 'Y';
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_STDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.FEE_INFO1[1-1].AMO_EDDT = SCCGWA.COMM_AREA.AC_DATE;
        BPCFFCON.FEE_INFO.ACCOUNT_BR = LNCSRPO.COMM_DATA.BR;
        S000_CALL_BPZFFCON();
        if (pgmRtn) return;
    }
    public void B052_FUND_LOAN_PROC() throws IOException,SQLException,Exception {
        B451_GET_FUND_SETL_INF();
        if (pgmRtn) return;
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            B452_FUND_P_SETL_PROCESS();
            if (pgmRtn) return;
        }
        if (LNCSRPO.COMM_DATA.TOT_I_AMT > 0 
            || LNCSRPO.COMM_DATA.TOT_O_AMT > 0 
            || LNCSRPO.COMM_DATA.TOT_L_AMT > 0) {
            B453_FUND_IOL_SETL_PROCESS();
            if (pgmRtn) return;
        }
        B454_FUND_AMT_PROCESS();
        if (pgmRtn) return;
    }
    public void B451_GET_FUND_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRFUND);
        IBS.init(SCCGWA, LNCRFUND);
        LNCRFUND.FUNC = 'I';
        LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
        S000_CALL_LNZRFUND();
        if (pgmRtn) return;
        if (LNRFUND.PAY_FLG == 'Y') {
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.SIGN = 'C';
            AICPQIA.CD.BUSI_KND = "LN01";
            AICPQIA.BR = LNRFUND.BOOK_BR;
            AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            WS_VARIABLES.P_TMP_AC = AICPQIA.AC;
            CEP.TRC(SCCGWA, WS_VARIABLES.P_TMP_AC);
            IBS.init(SCCGWA, AICPQIA);
            AICPQIA.SIGN = 'C';
            AICPQIA.CD.BUSI_KND = "LN02";
            AICPQIA.BR = LNRFUND.BOOK_BR;
            AICPQIA.CCY = LNCSRPO.COMM_DATA.CCY;
            S000_CALL_AIZPQIA();
            if (pgmRtn) return;
            WS_VARIABLES.I_TMP_AC = AICPQIA.AC;
            CEP.TRC(SCCGWA, WS_VARIABLES.I_TMP_AC);
        }
    }
    public void B452_FUND_P_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (LNRFUND.PAY_FLG == 'Y') {
            WS_VARIABLES.TX_AC_TYP = INTERNAL;
            WS_VARIABLES.TX_AC = WS_VARIABLES.P_TMP_AC;
        } else {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRFUND.PAY_P_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            WS_VARIABLES.TX_AC_TYP = WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP;
            WS_VARIABLES.TX_AC = LNRFUND.PAY_P_AC;
        }
        WS_VARIABLES.TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B453_FUND_IOL_SETL_PROCESS() throws IOException,SQLException,Exception {
        if (LNRFUND.PAY_FLG == 'Y') {
            WS_VARIABLES.TX_AC_TYP = INTERNAL;
            WS_VARIABLES.TX_AC = WS_VARIABLES.I_TMP_AC;
        } else {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRFUND.PAY_I_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            WS_VARIABLES.TX_AC_TYP = WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP;
            WS_VARIABLES.TX_AC = LNRFUND.PAY_I_AC;
        }
        WS_VARIABLES.TX_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT + LNCSRPO.COMM_DATA.TOT_O_AMT + LNCSRPO.COMM_DATA.TOT_L_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B454_FUND_AMT_PROCESS() throws IOException,SQLException,Exception {
        if (LNCSRPO.COMM_DATA.TOT_P_AMT > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = LNRFUND.FUND_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            B101_GET_AC_TYPE();
            if (pgmRtn) return;
            WS_VARIABLES.TX_AC_TYP = WS_VARIABLES.WS_SETL_INFO.FUND_AC_TYP;
            WS_VARIABLES.TX_AC = LNRFUND.FUND_AC;
            WS_VARIABLES.TX_AMT = LNCSRPO.COMM_DATA.TOT_P_AMT;
            B096_TX_AMT_PROC();
            if (pgmRtn) return;
        }
        if (LNRFUND.PAY_FLG == 'Y') {
            IBS.init(SCCGWA, LNRFUND);
            LNCRFUND.FUNC = 'R';
            LNRFUND.KEY.PROJ_NO = LNCCLNQ.DATA.PD_PROJ_NO;
            S000_CALL_LNZRFUND();
            if (pgmRtn) return;
            LNRFUND.TOT_PAY_P += LNCSRPO.COMM_DATA.TOT_P_AMT;
            LNRFUND.TOT_PAY_I += LNCSRPO.COMM_DATA.TOT_I_AMT;
            LNRFUND.TOT_PAY_I += LNCSRPO.COMM_DATA.TOT_O_AMT;
            LNRFUND.TOT_PAY_I += LNCSRPO.COMM_DATA.TOT_L_AMT;
            LNCRFUND.FUNC = 'U';
            S000_CALL_LNZRFUND();
            if (pgmRtn) return;
        }
    }
    public void B061_SOLD_OUT_LOAN_PROC() throws IOException,SQLException,Exception {
        WS_VARIABLES.TR_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        IBS.init(SCCGWA, LNRAGRE);
        LNRAGRE.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        T000_READ_LNTAGRE();
        if (pgmRtn) return;
        WS_VARIABLES.CTA_NO = LNRAGRE.REL_CTA_NO;
        B072_SOLD_OUT_SETL_PROCESS();
        if (pgmRtn) return;
        if (WS_VARIABLES.TR_TOT_AMT != 0) {
            B461_GET_SOLD_OUT_SETL_INF();
            if (pgmRtn) return;
            B462_SOLD_OUT_SETL_PROCESS();
            if (pgmRtn) return;
        }
    }
    public void B461_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.CI_TYPE = 'T';
        LNRSETL.KEY.SETTLE_TYPE = 'D';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNRSETL.AC);
        WS_VARIABLES.TX_AC_TYP = LNRSETL.AC_TYP;
        WS_VARIABLES.CTPY_AC = LNRSETL.AC;
    }
    public void B462_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_VARIABLES.TX_AC = WS_VARIABLES.CTPY_AC;
        WS_VARIABLES.TX_AMT = WS_VARIABLES.TR_TOT_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B473_GET_SOLD_OUT_SETL_INF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRSETL);
        IBS.init(SCCGWA, LNCRSETL);
        LNCRSETL.FUNC = 'I';
        LNRSETL.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRSETL.KEY.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRSETL.KEY.SETTLE_TYPE = '6';
        LNRSETL.KEY.CI_TYPE = 'E';
        S000_CALL_LNZRSETL();
        if (pgmRtn) return;
        WS_VARIABLES.TX_AC_TYP = LNRSETL.AC_TYP;
        WS_VARIABLES.CTPY_AC = LNRSETL.AC;
    }
    public void B474_AMT_SETL_PROCESS() throws IOException,SQLException,Exception {
        WS_VARIABLES.TX_AC = WS_VARIABLES.CTPY_AC;
        WS_VARIABLES.TX_AMT = WS_VARIABLES.LEFT_AMT;
        B095_TX_AMT_CR_PROC();
        if (pgmRtn) return;
    }
    public void B472_AMT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCIGEAI);
        LNCIGEAI.COMM_DATA.BUS_KND = "LNPMTAC";
        LNCIGEAI.COMM_DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        LNCIGEAI.COMM_DATA.AC_NO = WS_VARIABLES.SUSP_AC;
        LNCIGEAI.COMM_DATA.SIGN = 'D';
        S000_CALL_LNZIGEAI();
        if (pgmRtn) return;
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = LNCIGEAI.COMM_DATA.AI_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_VARIABLES.LEFT_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        S000_CALL_AIZUUPIAC();
        if (pgmRtn) return;
    }
    public void B071_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = WS_VARIABLES.CTA_NO;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_VARIABLES.DIFF_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_VARIABLES.DIFF_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_VARIABLES.DIFF_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.DIFF_I_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.DIFF_O_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.DIFF_L_AMT);
        WS_VARIABLES.LEFT_TOT_AMT = LNCSRPO.COMM_DATA.TOT_AMT;
        WS_VARIABLES.LEFT_AMT = WS_VARIABLES.LEFT_TOT_AMT;
        if (WS_VARIABLES.DIFF_I_AMT > 0) {
            if (WS_VARIABLES.LEFT_TOT_AMT > WS_VARIABLES.DIFF_I_AMT) {
                WS_VARIABLES.PAY_I_AMT = WS_VARIABLES.DIFF_I_AMT;
                LNRCTPY.I_PAY_AMT = LNRCTPY.I_REC_AMT;
                WS_VARIABLES.LEFT_AMT = WS_VARIABLES.LEFT_TOT_AMT - WS_VARIABLES.DIFF_I_AMT;
            } else {
                WS_VARIABLES.PAY_I_AMT = WS_VARIABLES.LEFT_TOT_AMT;
                LNRCTPY.I_PAY_AMT = LNRCTPY.I_PAY_AMT + WS_VARIABLES.LEFT_TOT_AMT;
                WS_VARIABLES.LEFT_AMT = 0;
            }
            WS_VARIABLES.LEFT_TOT_AMT = WS_VARIABLES.LEFT_AMT;
        }
        if (WS_VARIABLES.DIFF_O_AMT > 0) {
            if (WS_VARIABLES.LEFT_TOT_AMT > WS_VARIABLES.DIFF_O_AMT) {
                WS_VARIABLES.PAY_O_AMT = WS_VARIABLES.DIFF_O_AMT;
                LNRCTPY.O_PAY_AMT = LNRCTPY.O_REC_AMT;
                WS_VARIABLES.LEFT_AMT = WS_VARIABLES.LEFT_TOT_AMT - WS_VARIABLES.DIFF_O_AMT;
            } else {
                WS_VARIABLES.PAY_O_AMT = WS_VARIABLES.LEFT_TOT_AMT;
                LNRCTPY.O_PAY_AMT = LNRCTPY.O_PAY_AMT + WS_VARIABLES.LEFT_TOT_AMT;
                WS_VARIABLES.LEFT_AMT = 0;
            }
            WS_VARIABLES.LEFT_TOT_AMT = WS_VARIABLES.LEFT_AMT;
        }
        if (WS_VARIABLES.DIFF_L_AMT > 0) {
            if (WS_VARIABLES.LEFT_TOT_AMT > WS_VARIABLES.DIFF_L_AMT) {
                WS_VARIABLES.PAY_L_AMT = WS_VARIABLES.DIFF_L_AMT;
                LNRCTPY.L_PAY_AMT = LNRCTPY.L_REC_AMT;
                WS_VARIABLES.LEFT_AMT = WS_VARIABLES.LEFT_TOT_AMT - WS_VARIABLES.DIFF_L_AMT;
            } else {
                WS_VARIABLES.PAY_L_AMT = WS_VARIABLES.LEFT_TOT_AMT;
                LNRCTPY.L_PAY_AMT = LNRCTPY.L_PAY_AMT + WS_VARIABLES.LEFT_TOT_AMT;
                WS_VARIABLES.LEFT_AMT = 0;
            }
            WS_VARIABLES.LEFT_TOT_AMT = WS_VARIABLES.LEFT_AMT;
        }
        CEP.TRC(SCCGWA, WS_VARIABLES.LEFT_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_I_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_O_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_L_AMT);
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B072_SOLD_OUT_SETL_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRCTPY);
        LNRCTPY.KEY.CONTRACT_NO = WS_VARIABLES.CTA_NO;
        T000_READ_UPDATE_LNTCTPY();
        if (pgmRtn) return;
        WS_VARIABLES.DIFF_I_AMT = LNRCTPY.I_REC_AMT - LNRCTPY.I_PAY_AMT;
        WS_VARIABLES.DIFF_O_AMT = LNRCTPY.O_REC_AMT - LNRCTPY.O_PAY_AMT;
        WS_VARIABLES.DIFF_L_AMT = LNRCTPY.L_REC_AMT - LNRCTPY.L_PAY_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.DIFF_I_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.DIFF_O_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.DIFF_L_AMT);
        if (WS_VARIABLES.DIFF_I_AMT < LNCSRPO.COMM_DATA.TOT_I_AMT) {
            WS_VARIABLES.PAY_I_AMT = WS_VARIABLES.DIFF_I_AMT;
        } else {
            WS_VARIABLES.PAY_I_AMT = LNCSRPO.COMM_DATA.TOT_I_AMT;
        }
        if (WS_VARIABLES.DIFF_O_AMT < LNCSRPO.COMM_DATA.TOT_O_AMT) {
            WS_VARIABLES.PAY_O_AMT = WS_VARIABLES.DIFF_O_AMT;
        } else {
            WS_VARIABLES.PAY_O_AMT = LNCSRPO.COMM_DATA.TOT_O_AMT;
        }
        if (WS_VARIABLES.DIFF_L_AMT < LNCSRPO.COMM_DATA.TOT_L_AMT) {
            WS_VARIABLES.PAY_L_AMT = WS_VARIABLES.DIFF_L_AMT;
        } else {
            WS_VARIABLES.PAY_L_AMT = LNCSRPO.COMM_DATA.TOT_L_AMT;
        }
        LNRCTPY.I_PAY_AMT += WS_VARIABLES.PAY_I_AMT;
        LNRCTPY.O_PAY_AMT += WS_VARIABLES.PAY_O_AMT;
        LNRCTPY.L_PAY_AMT += WS_VARIABLES.PAY_L_AMT;
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_I_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_O_AMT);
        CEP.TRC(SCCGWA, WS_VARIABLES.PAY_L_AMT);
        T000_REWRITE_LNTCTPY();
        if (pgmRtn) return;
    }
    public void B095_TX_AMT_CR_PROC() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase(INTERNAL)) {
            B095_TX_IA_CR_PROC();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase(IB_AC)) {
            B095_TX_IB_CR_PROC();
            if (pgmRtn) return;
        } else {
            B095_TX_DD_CR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IA_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_VARIABLES.TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "CR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_VARIABLES.TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIAC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_IB_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_VARIABLES.TX_AC;
        IBCPOSTA.CCY = LNCSRPO.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_VARIABLES.TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'C';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSRPO.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZCRAC();
            if (pgmRtn) return;
        }
    }
    public void B095_TX_DD_CR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_TYPE = 'T';
        if (WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase("05") 
            || WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase("06")) {
            DDCUCRAC.CARD_NO = WS_VARIABLES.TX_AC;
        } else {
            DDCUCRAC.AC = WS_VARIABLES.TX_AC;
        }
        DDCUCRAC.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUCRAC.CCY_TYPE = LNCSRPO.COMM_DATA.CCY_TYPE.charAt(0);
        DDCUCRAC.TX_AMT = WS_VARIABLES.TX_AMT;
        DDCUCRAC.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        DDCUCRAC.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        DDCUDRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        DDCUCRAC.OTHER_CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUCRAC.OTHER_AMT = WS_VARIABLES.TX_AMT;
        if (DDCUCRAC.TX_AMT != 0) {
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_AMT_PROC() throws IOException,SQLException,Exception {
        if (WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase(INTERNAL)) {
            B096_TX_IA_DR_PROC();
            if (pgmRtn) return;
        } else if (WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase(IB_AC)) {
            B096_TX_IB_DR_PROC();
            if (pgmRtn) return;
        } else {
            B096_TX_DD_DR_PROC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_IA_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = WS_VARIABLES.TX_AC;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        AICUUPIA.DATA.CCY = LNCSRPO.COMM_DATA.CCY;
        AICUUPIA.DATA.AMT = WS_VARIABLES.TX_AMT;
        AICUUPIA.DATA.VALUE_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        AICUUPIA.DATA.RVS_NO = " ";
        AICUUPIA.DATA.PAY_MAN = CICQACAC.O_DATA.O_ACR_DATA.O_AC_CNM;
        if (AICUUPIA.DATA.AMT != 0) {
            S000_CALL_AIZUUPIA();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_IB_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.NOSTRO_CD = WS_VARIABLES.TX_AC;
        IBCPOSTA.CCY = LNCSRPO.COMM_DATA.CCY;
        IBCPOSTA.AMT = WS_VARIABLES.TX_AMT;
        IBCPOSTA.TXTYPE = "01";
        IBCPOSTA.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.ACT_CTR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBCPOSTA.ACT_CTR = LNCSRPO.COMM_DATA.BR;
        IBCPOSTA.OUR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.THR_REF = LNCSRPO.COMM_DATA.CTA_NO;
        IBCPOSTA.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        if (IBCPOSTA.AMT != 0) {
            S000_CALL_IBZDRAC();
            if (pgmRtn) return;
        }
    }
    public void B096_TX_DD_DR_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.BANK_DR_FLG = 'Y';
        DDCUDRAC.TX_BAL_FLG = 'N';
        DDCUDRAC.TSTS_TABL = "0014";
        if (WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase("05") 
            || WS_VARIABLES.TX_AC_TYP.equalsIgnoreCase("06")) {
            DDCUDRAC.CARD_NO = WS_VARIABLES.TX_AC;
        } else {
            DDCUDRAC.AC = WS_VARIABLES.TX_AC;
        }
        DDCUDRAC.CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.TX_AMT = WS_VARIABLES.TX_AMT;
        DDCUDRAC.VAL_DATE = LNCSRPO.COMM_DATA.TR_VAL_DTE;
        DDCUDRAC.TX_MMO = LNCSRPO.COMM_DATA.MMO;
        DDCUDRAC.OTHER_AC = LNRAGRE.PAPER_NO;
        DDCUDRAC.OTHER_CCY = LNCSRPO.COMM_DATA.CCY;
        DDCUDRAC.OTHER_AMT = WS_VARIABLES.TX_AMT;
        if (DDCUDRAC.TX_AMT != 0) {
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
    }
    public void B200_GET_DUE_DAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRCVD);
        LNRRCVD.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRRCVD.KEY.SUB_CTA_NO = 0;
        LNTRCVD_RD = new DBParm();
        LNTRCVD_RD.TableName = "LNTRCVD";
        LNTRCVD_RD.eqWhere = "CONTRACT_NO,SUB_CTA_NO";
        LNTRCVD_RD.where = "REPY_STS < > '2'";
        LNTRCVD_RD.fst = true;
        LNTRCVD_RD.order = "DUE_DT";
        IBS.READ(SCCGWA, LNRRCVD, this, LNTRCVD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = LNRRCVD.DUE_DT;
            SCCCLDT.DATE2 = LNCSRPO.COMM_DATA.TR_VAL_DTE;
            CEP.TRC(SCCGWA, LNRRCVD.DUE_DT);
            CEP.TRC(SCCGWA, LNCSRPO.COMM_DATA.TR_VAL_DTE);
            S000_CALL_SCCCLDT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            WS_VARIABLES.D_O_DAY = SCCCLDT.DAYS;
        } else {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                WS_VARIABLES.D_O_DAY = 0;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B801_RECORD_CLRR_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNCSCLRR);
        IBS.init(SCCGWA, LNCTRANM);
        LNCTRANM.FUNC = '4';
        LNCTRANM.REC_DATA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNCTRANM.REC_DATA.KEY.SUB_CTA_NO = 0;
        LNCTRANM.REC_DATA.KEY.REC_FLAG = 'B';
        LNCTRANM.REC_DATA.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNCTRANM.REC_DATA.KEY.TR_JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        LNCTRANM.REC_DATA.KEY.TXN_TYP = 'T';
        LNCTRANM.REC_DATA.KEY.TXN_TERM = 0;
        LNCTRANM.REC_DATA.KEY.TRAN_FLG = 'C';
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        WS_VARIABLES.WS_CLR_DATA.CLR_CTA_NO = LNCSCLR.COMM_DATA.CTA_NO;
        WS_VARIABLES.WS_CLR_DATA.CLR_TR_VAL_DT = LNCSCLR.COMM_DATA.TR_VAL_DT;
        WS_VARIABLES.WS_CLR_DATA.CLR_REC_AC2 = LNCSCLR.COMM_DATA.PAY_DATA[1-1].REC_AC2;
        WS_VARIABLES.WS_CLR_DATA.CLR_NOR_P = LNCSCLR.COMM_DATA.NOR_P;
        WS_VARIABLES.WS_CLR_DATA.CLR_OVR_P = LNCSCLR.COMM_DATA.OVR_P;
        WS_VARIABLES.WS_CLR_DATA.CLR_NOR_I = LNCSCLR.COMM_DATA.NOR_I;
        WS_VARIABLES.WS_CLR_DATA.CLR_OVR_I = LNCSCLR.COMM_DATA.OVR_I;
        WS_VARIABLES.WS_CLR_DATA.CLR_NOR_O = LNCSCLR.COMM_DATA.NOR_O;
        WS_VARIABLES.WS_CLR_DATA.CLR_NOR_L = LNCSCLR.COMM_DATA.NOR_L;
        WS_VARIABLES.WS_CLR_DATA.CLR_PC_CLR_FLG = LNCSCLR.COMM_DATA.PC_CLR_FLG;
        WS_VARIABLES.WS_CLR_DATA.CLR_P_CLR_FLG = LNCSCLR.COMM_DATA.P_CLR_FLG;
        WS_VARIABLES.WS_CLR_DATA.CLR_I_CLR_FLG = LNCSCLR.COMM_DATA.I_CLR_FLG;
        LNCTRANM.FUNC = '2';
        WS_VARIABLES.CLR_DATA_LEN = 1640;
        if (LNCTRANM.REC_DATA.TR_DATA == null) LNCTRANM.REC_DATA.TR_DATA = "";
        JIBS_tmp_int = LNCTRANM.REC_DATA.TR_DATA.length();
        for (int i=0;i<1024-JIBS_tmp_int;i++) LNCTRANM.REC_DATA.TR_DATA += " ";
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_CLR_DATA);
        LNCTRANM.REC_DATA.TR_DATA = JIBS_tmp_str[0] + LNCTRANM.REC_DATA.TR_DATA.substring(WS_VARIABLES.CLR_DATA_LEN);
        S000_CALL_LNZTRANM();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_P);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.OVR_P);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_I);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.OVR_I);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_O);
        CEP.TRC(SCCGWA, LNCSCLR.COMM_DATA.NOR_L);
    }
    public void R000_MEMORY_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.BP_EWA_CNT);
        CEP.TRC(SCCGWA, WS_VARIABLES.GBPA_EWA_CNT);
        CEP.TRC(SCCGWA, WS_VARIABLES.GBPA_FEE_CNT);
        CEP.TRC(SCCGWA, WS_VARIABLES.GBPA_FHIS_CUR_SEQ);
        CEP.TRC(SCCGWA, WS_VARIABLES.GBPA_NFHIS_CUR_SEQ);
        CEP.TRC(SCCGWA, WS_VARIABLES.GSCA_BA_CNT);
        CEP.TRC(SCCGWA, LNCGCFEE.FEE_DATA);
        BPREWA.BASIC_AREA.CNT = WS_VARIABLES.BP_EWA_CNT;
        EVENTS = new BPREWA15_EVENTS();
        BPREWA.EVENTS.add(EVENTS);
        BP_AREA.EWA_AREA.EWA_CNT = WS_VARIABLES.GBPA_EWA_CNT;
        BP_AREA.FEE_AREA.FEE_CNT = WS_VARIABLES.GBPA_FEE_CNT;
        BP_AREA.FHIS_CUR_SEQ = WS_VARIABLES.GBPA_FHIS_CUR_SEQ;
        BP_AREA.NFHIS_CUR_SEQ = WS_VARIABLES.GBPA_NFHIS_CUR_SEQ;
        SC_AREA.BA_CNT = WS_VARIABLES.GSCA_BA_CNT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCGCFEE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCGCFEE);
    }
    public void S000_CALL_DDZUOBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DDZUOBAL", DDCUOBAL);
        if (DDCUOBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCUOBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZURPN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-UNT-REPAY-NORMAL", LNCURPN);
        if (LNCURPN.RC.RC_RTNCODE != 0) {
            WS_VARIABLES.WS_ERR_MSG.ERR_MSG_APP = LNCURPN.RC.RC_APP;
            WS_VARIABLES.WS_ERR_MSG.ERR_MSG_RTNCODE = LNCURPN.RC.RC_RTNCODE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCCCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_VARIABLES.WS_ERR_MSG.ERR_MSG_APP = "SC";
            WS_VARIABLES.WS_ERR_MSG.ERR_MSG_RTNCODE = SCCCLDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSPDQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-PSTDUE-ENQ", LNCSPDQ, true);
        if (LNCSPDQ.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSPDQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT2 = new SCSSCLDT();
        SCSSCLDT2.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_VARIABLES.WS_ERR_MSG.ERR_MSG_APP = "SC";
            WS_VARIABLES.WS_ERR_MSG.ERR_MSG_RTNCODE = SCCCLDT.RC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZPCFTA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-P-CHK-FTA-TYP", LNCPCFTA);
        if (LNCPCFTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCPCFTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZAPRDM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-APRD-MAINT", LNCAPRDM);
        if (LNCAPRDM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCAPRDM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICRCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-I-COMMITMENT-USE", LNCICRCM);
        if (LNCICRCM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICRCM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        WS_VARIABLES.BP_EWA_CNT = BPREWA.BASIC_AREA.CNT;
        WS_VARIABLES.GBPA_EWA_CNT = BP_AREA.EWA_AREA.EWA_CNT;
        WS_VARIABLES.GBPA_FEE_CNT = BP_AREA.FEE_AREA.FEE_CNT;
        WS_VARIABLES.GBPA_FHIS_CUR_SEQ = BP_AREA.FHIS_CUR_SEQ;
        WS_VARIABLES.GBPA_NFHIS_CUR_SEQ = BP_AREA.NFHIS_CUR_SEQ;
        WS_VARIABLES.GSCA_BA_CNT = SC_AREA.BA_CNT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGCFEE);
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "DD-UNIT-DRAW-PROC";
        SCCCALL.COMMAREA_PTR = DDCUDRAC;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
            CEP.TRC(SCCGWA, "DDZUDRAC SUCC");
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC, true);
    }
    public void S000_CALL_IBZDRAC() throws IOException,SQLException,Exception {
        WS_VARIABLES.BP_EWA_CNT = BPREWA.BASIC_AREA.CNT;
        WS_VARIABLES.GBPA_EWA_CNT = BP_AREA.EWA_AREA.EWA_CNT;
        WS_VARIABLES.GBPA_FEE_CNT = BP_AREA.FEE_AREA.FEE_CNT;
        WS_VARIABLES.GBPA_FHIS_CUR_SEQ = BP_AREA.FHIS_CUR_SEQ;
        WS_VARIABLES.GBPA_NFHIS_CUR_SEQ = BP_AREA.NFHIS_CUR_SEQ;
        WS_VARIABLES.GSCA_BA_CNT = SC_AREA.BA_CNT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGCFEE);
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "IB-DEBIT-AC";
        SCCCALL.COMMAREA_PTR = IBCPOSTA;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (IBCPOSTA.RC.RC_CODE != 0) {
    }
    public void S000_CALL_IBZCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-CREDIT-AC", IBCPOSTA);
        if (IBCPOSTA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCPOSTA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPFHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PROC-FHIS", BPCPFHIS);
        if (BPCPFHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPFHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZSQBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-SVR-DDZSQBAL", DDCSQBAL);
        if (DDCSQBAL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCSQBAL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRSETL() throws IOException,SQLException,Exception {
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 716;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        if (LNCRSETL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRSETL_2() throws IOException,SQLException,Exception {
        LNCRSETL.REC_PTR = LNRSETL;
        LNCRSETL.REC_LEN = 716;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTSETL", LNCRSETL);
        if (LNCRSETL.RC.RC_CODE != 0 
            && LNCRSETL.RC.RC_CODE != 1568) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRSETL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST, true);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_SCZTPCL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "SC-LINK-EXTSERV", SCCTPCL);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        JIBS_tmp_str[1] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
        if (AICPQIA.RC.RC_CODE != 0 
            || JIBS_tmp_str[1].trim().length() == 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRPOR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-REPAY-DELETE", LNCSRPOR);
    }
    public void S000_CALL_LNZSSUNM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-SUN-MAINT", LNCSSUNM);
        if (LNCSSUNM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSSUNM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSRLSR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSRLSR", GDCSRLSR, true);
    }
    public void S000_CALL_LNZICTLM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-ICTL-MAINT", LNCICTLM);
        if (LNCICTLM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCICTLM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZTRANM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRC-TRAN-MAINT", LNCTRANM);
        CEP.TRC(SCCGWA, LNCTRANM.RC.RC_APP);
        if (LNCTRANM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCTRANM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_LNTRELA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRRELA);
        LNRRELA.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRRELA.KEY.TYPE = 'S';
        LNTRELA_RD = new DBParm();
        LNTRELA_RD.TableName = "LNTRELA";
        LNTRELA_RD.where = "CONTRACT_NO = :LNRRELA.KEY.CONTRACT_NO "
            + "AND TYPE = :LNRRELA.KEY.TYPE";
        IBS.READ(SCCGWA, LNRRELA, this, LNTRELA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_NOFND_RELA, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B0000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        LNTICTL_RD.where = "CONTRACT_NO = :LNRICTL.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRICTL, this, LNTICTL_RD);
    }
    public void B0000_READ_LNTAPRD() throws IOException,SQLException,Exception {
        LNTAPRD_RD = new DBParm();
        LNTAPRD_RD.TableName = "LNTAPRD";
        LNTAPRD_RD.where = "CONTRACT_NO = :LNRAPRD.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAPRD, this, LNTAPRD_RD);
    }
    public void T000_READ_UPDATE_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        LNTSUBS_RD.upd = true;
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SUBS_NOTFND, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_REWRITE_LNTSUBS() throws IOException,SQLException,Exception {
        LNRSUBS.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRSUBS.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.REWRITE(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SUBS_NOTFND, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTCTPY1() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.where = "CONTRACT_NO = :LNRCTPY.KEY.CONTRACT_NO "
            + "AND TR_TYP = :LNRCTPY.KEY.TR_TYP";
        LNTCTPY_RD.fst = true;
        LNTCTPY_RD.order = "TRAN_DATE DESC";
        IBS.READ(SCCGWA, LNRCTPY, this, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_READ_LNTCTPY_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_LNTPACK() throws IOException,SQLException,Exception {
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        IBS.READ(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PACK_NOTFND, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_UPDATE_LNTPACK() throws IOException,SQLException,Exception {
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        LNTPACK_RD.upd = true;
        IBS.READ(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PACK_NOTFND, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_REWRITE_LNTPACK() throws IOException,SQLException,Exception {
        LNRPACK.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPACK.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPACK_RD = new DBParm();
        LNTPACK_RD.TableName = "LNTPACK";
        IBS.REWRITE(SCCGWA, LNRPACK, LNTPACK_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PACK_REWRITE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_UPDATE_LNTPLDT() throws IOException,SQLException,Exception {
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        LNTPLDT_RD.upd = true;
        IBS.READ(SCCGWA, LNRPLDT, LNTPLDT_RD);
        WS_COND_FLG.READ_LNTPLDT_FLG = ' ';
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.READ_LNTPLDT_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.READ_LNTPLDT_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTPLDT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTPLDT() throws IOException,SQLException,Exception {
        LNRPLDT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        IBS.REWRITE(SCCGWA, LNRPLDT, LNTPLDT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PLDT_REWRITE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_LNTPLDT() throws IOException,SQLException,Exception {
        LNRPLDT.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNRPLDT.KEY.TR_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.KEY.BTH_PK = LNRPACK.KEY.BTH_PK;
        LNRPLDT.CCY = LNCSRPO.COMM_DATA.CCY;
        LNRPLDT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNRPLDT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRPLDT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTPLDT_RD = new DBParm();
        LNTPLDT_RD.TableName = "LNTPLDT";
        LNTPLDT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, LNRPLDT, LNTPLDT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_PLDT_WRITE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSBAAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SRV-GDZSBAAC", GDCSTRAC);
    }
    public void S000_CALL_GDZSTRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "GD-S-TRAC-PROC";
        SCCCALL.COMMAREA_PTR = GDCSTRAC;
        SCCCALL.ERRHDL_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        WS_VARIABLES.BP_EWA_CNT = BPREWA.BASIC_AREA.CNT;
        WS_VARIABLES.GBPA_EWA_CNT = BP_AREA.EWA_AREA.EWA_CNT;
        WS_VARIABLES.GBPA_FEE_CNT = BP_AREA.FEE_AREA.FEE_CNT;
        WS_VARIABLES.GBPA_FHIS_CUR_SEQ = BP_AREA.FHIS_CUR_SEQ;
        WS_VARIABLES.GBPA_NFHIS_CUR_SEQ = BP_AREA.NFHIS_CUR_SEQ;
        WS_VARIABLES.GSCA_BA_CNT = SC_AREA.BA_CNT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGCFEE);
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "BP-U-ADD-CBOX";
        SCCCALL.COMMAREA_PTR = BPCUABOX;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        WS_VARIABLES.BP_EWA_CNT = BPREWA.BASIC_AREA.CNT;
        WS_VARIABLES.GBPA_EWA_CNT = BP_AREA.EWA_AREA.EWA_CNT;
        WS_VARIABLES.GBPA_FEE_CNT = BP_AREA.FEE_AREA.FEE_CNT;
        WS_VARIABLES.GBPA_FHIS_CUR_SEQ = BP_AREA.FHIS_CUR_SEQ;
        WS_VARIABLES.GBPA_NFHIS_CUR_SEQ = BP_AREA.NFHIS_CUR_SEQ;
        WS_VARIABLES.GSCA_BA_CNT = SC_AREA.BA_CNT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGCFEE);
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "AI-U-UPDATE-IA";
        SCCCALL.COMMAREA_PTR = AICUUPIA;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (AICUUPIA.RC.RC_CODE != 0) {
    }
    public void S000_CALL_AIZUUPIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZUUPIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
        if (AICUUPIA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICUUPIA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSRPC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SRV-REPAY-CALC", LNCSRPC);
        if (LNCSRPC.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSRPC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC, true);
        CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACAC.RC);
        }
    }
    public void S000_CALL_LNZRCMMT() throws IOException,SQLException,Exception {
        LNCRCMMT.REC_PTR = LNRCMMT;
        LNCRCMMT.REC_LEN = 1710;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTCMMT", LNCRCMMT);
        CEP.TRC(SCCGWA, LNCRCMMT.RC);
        if (LNCRCMMT.RC.RC_CODE != 0 
            && LNCRCMMT.RETURN_INFO != 'N' 
            && LNCRCMMT.RC.RC_CODE != 1538) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRCMMT.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRFUND() throws IOException,SQLException,Exception {
        LNCRFUND.REC_PTR = LNRFUND;
        LNCRFUND.REC_LEN = 627;
        IBS.CALLCPN(SCCGWA, "LN-R-FUND-MAIN", LNCRFUND);
        if (LNCRFUND.RETURN_INFO != 'F') {
            IBS.CPY2CLS(SCCGWA, LNCRFUND.RC.RC_CODE+"", WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZRICTL() throws IOException,SQLException,Exception {
        LNCRICTL.REC_PTR = LNRICTL;
        LNCRICTL.REC_LEN = 1280;
        IBS.CALLCPN(SCCGWA, "LN-RSC-LNTICTL", LNCRICTL);
        if (LNCRICTL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCRICTL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTICTL() throws IOException,SQLException,Exception {
        LNTICTL_RD = new DBParm();
        LNTICTL_RD.TableName = "LNTICTL";
        IBS.READ(SCCGWA, LNRICTL, LNTICTL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_COND_FLG.READ_LNTICTL_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_COND_FLG.READ_LNTICTL_FLG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "LNTICTL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSUBS() throws IOException,SQLException,Exception {
        LNTSUBS_RD = new DBParm();
        LNTSUBS_RD.TableName = "LNTSUBS";
        IBS.READ(SCCGWA, LNRSUBS, LNTSUBS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SUBS_NOTFND, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_SYS_ERR, WS_VARIABLES.WS_ERR_MSG);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_UPDATE_LNTCTPY() throws IOException,SQLException,Exception {
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        LNTCTPY_RD.upd = true;
        IBS.READ(SCCGWA, LNRCTPY, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_READ_LNTCTPY_ERR, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_LNTCTPY() throws IOException,SQLException,Exception {
        LNRCTPY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        LNRCTPY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        LNTCTPY_RD = new DBParm();
        LNTCTPY_RD.TableName = "LNTCTPY";
        IBS.REWRITE(SCCGWA, LNRCTPY, LNTCTPY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_UPD_LNTCTPY_ERR, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZIQLDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-I-QLDR-PROC", GDCIQLDR);
        if (GDCIQLDR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, GDCIQLDR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZUMPLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-U-PLDR-RL", GDCUMPLD);
    }
    public void S000_CALL_GDZUMPDR() throws IOException,SQLException,Exception {
        WS_VARIABLES.BP_EWA_CNT = BPREWA.BASIC_AREA.CNT;
        WS_VARIABLES.GBPA_EWA_CNT = BP_AREA.EWA_AREA.EWA_CNT;
        WS_VARIABLES.GBPA_FEE_CNT = BP_AREA.FEE_AREA.FEE_CNT;
        WS_VARIABLES.GBPA_FHIS_CUR_SEQ = BP_AREA.FHIS_CUR_SEQ;
        WS_VARIABLES.GBPA_NFHIS_CUR_SEQ = BP_AREA.NFHIS_CUR_SEQ;
        WS_VARIABLES.GSCA_BA_CNT = SC_AREA.BA_CNT;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCGCFEE);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], LNCGCFEE);
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "GD-U-PLDR-DR";
        SCCCALL.COMMAREA_PTR = GDCUMPDR;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
        if (SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE == 'E') {
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
        CEP.TRC(SCCGWA, CICQACRI.RC.RC_CODE);
        if (CICQACRI.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICQACRI.RC);
        }
    }
    public void T000_READ_LNTAGRE() throws IOException,SQLException,Exception {
        LNTAGRE_RD = new DBParm();
        LNTAGRE_RD.TableName = "LNTAGRE";
        LNTAGRE_RD.where = "CONTRACT_NO = :LNRAGRE.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRAGRE, this, LNTAGRE_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_AGRE_NOTFND, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTTRAN() throws IOException,SQLException,Exception {
        LNRTRAN.KEY.TR_JRN_NO = BP_AREA.CANCEL_AREA.CAN_JRN_NO;
        LNRTRAN.KEY.CONTRACT_NO = LNCSRPO.COMM_DATA.CTA_NO;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.where = "CONTRACT_NO = :LNRTRAN.KEY.CONTRACT_NO "
            + "AND REC_FLAG = 'B' "
            + "AND TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO";
        LNTTRAN_RD.fst = true;
        LNTTRAN_RD.order = "TR_DATE DESC";
        IBS.READ(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
        LNCSRPO.COMM_DATA.TOT_AMT = LNRTRAN.PAY_AMT1;
        LNCSRPO.COMM_DATA.TOT_P_AMT = LNRTRAN.P_AMT;
        LNCSRPO.COMM_DATA.TOT_I_AMT = LNRTRAN.I_AMT;
        LNCSRPO.COMM_DATA.TOT_O_AMT = LNRTRAN.O_AMT;
        LNCSRPO.COMM_DATA.TOT_L_AMT = LNRTRAN.L_AMT;
    }
    public void T000_READ_LNTCONT() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        IBS.READ(SCCGWA, LNRCONT, LNTCONT_RD);
    }
    public void S000_CALL_BPZFFTXI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-TX-INFO", BPCFFTXI);
        if (BPCFFTXI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFTXI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFFCON() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-F-CON-CHG-INFO", BPCFFCON);
        if (BPCFFCON.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFFCON.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFSSVR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FSVR", BPCOFSVR);
        if (BPCOFSVR.FOUND_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, ERROR_MSG.LN_ERR_FEE_CODE, WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZICLNQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-LN-INFO", LNCCLNQ);
        if (LNCCLNQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCCLNQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_GDZSDDDR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "GD-SVR-GDZSDDDR", GDCSDDDR);
    }
    public void S000_CALL_AIZPQITM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-ITM", AICPQITM);
        if (AICPQITM.RC.RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQITM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTCONT2() throws IOException,SQLException,Exception {
        LNTCONT_RD = new DBParm();
        LNTCONT_RD.TableName = "LNTCONT";
        LNTCONT_RD.where = "CONTRACT_NO = :LNRCONT.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRCONT, this, LNTCONT_RD);
    }
    public void T000_READ_LNTLOAN() throws IOException,SQLException,Exception {
        LNTLOAN_RD = new DBParm();
        LNTLOAN_RD.TableName = "LNTLOAN";
        LNTLOAN_RD.where = "CONTRACT_NO = :LNRLOAN.KEY.CONTRACT_NO";
        IBS.READ(SCCGWA, LNRLOAN, this, LNTLOAN_RD);
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZIGEAI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-FUNC-AI-AC", LNCIGEAI);
        if (LNCIGEAI.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCIGEAI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_LNZSCLR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "LN-SVR-CLEAR", LNCSCLR);
        if (LNCSCLR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, LNCSCLR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_VARIABLES.WS_ERR_MSG);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_LNTSETL() throws IOException,SQLException,Exception {
        LNTSETL_RD = new DBParm();
        LNTSETL_RD.TableName = "LNTSETL";
        LNTSETL_RD.eqWhere = "SETTLE_TYPE,CONTRACT_NO";
        IBS.READ(SCCGWA, LNRSETL, LNTSETL_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_VARIABLES.WS_ERR_MSG);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_VARIABLES.ERR_INFO);
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
